package com.indeed;

import com.indeed.annotation.Progress;
import com.indeed.builder.SearchURIBuilder;
import com.indeed.control.DataExtractor;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.ParsingSearchResults;
import com.indeed.entity.SearchResult;
import com.indeed.parser.SearchResultsParser;
import com.indeed.presentation.other.SearchProgress;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class Indeed {

    @Inject
    private SearchResultsStore searchResultsStore;

    @Inject
    private SearchURIBuilder searchURIBuilder;

    @Inject
    private SearchResultsParser parser;

    @Inject
    private DataExtractor dataExtractor;

    private SearchProgress searchProgress;

    @Inject @Progress
    Event<SearchProgress> searchProgressEvent;

    private static final Integer limit = 25;

    public List<SearchResult> getAllParsedJobs() {
        return searchResultsStore.findByNativeQuery(SearchResult.ALL);
    }

    public Integer getTotalSearchResults(String query, String location, Integer limit) throws URISyntaxException, IOException, ParseException {
        URI uri;

        uri = searchURIBuilder.getURIForFromage(query, location, 0, limit, 15);
        URLConnection urlConnection = uri.toURL().openConnection();
        parser.setJsonParser(urlConnection.getInputStream());
        return parser.parse().getTotalResults();
    }

    public List<SearchResult> getNewJobs(String query, String location, Integer limit) throws URISyntaxException, IOException, ParseException {
        System.out.println("Get new jobs started....");

        List<SearchResult> newJobs = new ArrayList<>();

        Integer position = 0;
        Integer total;
        URI uri;
        ParsingSearchResults results;

        do {
            uri = searchURIBuilder.getURIForFromage(query, location, position, limit, 15);
            System.out.println("uri: " + uri.toString());

            URLConnection urlConnection =  uri.toURL().openConnection();
            urlConnection.setConnectTimeout(5000);
            parser.setJsonParser(urlConnection.getInputStream());
            results = parser.parse();

            total = results.getTotalResults();

            if (total == 0) return null;

            for (SearchResult result : results.getSearchResults()) {

                if (!jobExists(result)) {
                    result = dataExtractor.appendContactData(result);
                    newJobs.add(result);
                }

                position++;
            }

            searchProgress.setCurrent(position);
            searchProgressEvent.fire(searchProgress);

        } while (position < total);

        return newJobs;
    }

    public void startSearching(String query, String location) {
        Integer totalSearchResults;
        searchProgress = new SearchProgress();

        try {
            totalSearchResults = getTotalSearchResults(query, location, limit);

            searchProgress.setTotal(totalSearchResults);
            List <SearchResult> searchResults = getNewJobs(query, location, limit);

            System.out.println("b4 saving");
            for (SearchResult result : searchResults) {
                if (!jobExists(result)) {
                    searchResultsStore.create(result);
                }
            }

        } catch (URISyntaxException | IOException | ParseException e) {
            Logger.getLogger(Indeed.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private Boolean jobExists(SearchResult searchResult) {
        List results;
        Map<String, String> params = new HashMap<>();

        params.put("jobKey", searchResult.getJobKey());

        results = searchResultsStore.findWithNamedQuery(SearchResult.GET_BY_JOB_KEY,params,1);

        if (results.size() >= 1) {
            System.out.println("Job with key " + searchResult.getJobKey() + " already exists!");
            return true;
        }

        return false;
    }

}
