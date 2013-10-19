package com.indeed.control;

import com.indeed.builder.SearchURIBuilder;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.ParsingSearchResults;
import com.indeed.domain.search_result.SearchResult;
import com.indeed.parser.SearchResultsParser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Local
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

    protected List<SearchResult> getNewJobs(Query query) throws URISyntaxException, IOException, ParseException {
        System.out.println("Get new jobs started....");

        List<SearchResult> newJobs = new ArrayList<>();

        Integer position = 0;
        Integer total;
        URI uri;
        ParsingSearchResults results;

        do {
            uri = searchURIBuilder.getURIForFromage(query.getQuery(), query.getLocation(), position, limit, query.getDaysBack());
            System.out.println("uri: " + uri.toString());

            URLConnection urlConnection =  uri.toURL().openConnection();
            urlConnection.setConnectTimeout(5000);
            parser.setJsonParser(urlConnection.getInputStream());
            results = parser.parse();

            total = results.getTotalResults();

            if (total == 0) return Collections.emptyList();

            for (SearchResult result : results.getSearchResults()) {

                if (!jobExists(result)) {
                    result = dataExtractor.appendContactData(result);
                    result.setQueryType(query.getName());
                    newJobs.add(result);
                }

                position++;
            }

        } while (position < total);

        return newJobs;
    }

    public void search(Query query) {
        try {

            for (SearchResult result : getNewJobs(query)) {
                searchResultsStore.create(result);
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
