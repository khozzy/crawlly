package com.indeed;

import com.indeed.builder.SearchURIBuilder;
import com.indeed.control.DataExtractor;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.ParsingSearchResults;
import com.indeed.entity.SearchResult;
import com.indeed.parser.SearchResultsParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<SearchResult> getAllParsedJobs() {
        return searchResultsStore.findByNativeQuery(SearchResult.ALL);
    }

    public Integer getTotalSearchResults(String query, String location, Integer limit) throws URISyntaxException, IOException, ParseException {
        URI uri;

        uri = searchURIBuilder.getURIForPagination(query,location,0,limit);
        URLConnection urlConnection = uri.toURL().openConnection();
        parser.setJsonParser(urlConnection.getInputStream());
        return parser.parse().getTotalResults();

    }

    public List<SearchResult> getNewJobs(String query, String location, Integer limit) throws URISyntaxException, IOException, ParseException {
        List<SearchResult> newJobs = new ArrayList<>();

        Integer position = 0;
        Integer total;
        URI uri;
        ParsingSearchResults results;

        do {
            uri = searchURIBuilder.getURIForPagination(query,location, position, limit);
            URLConnection urlConnection =  uri.toURL().openConnection();
            urlConnection.setConnectTimeout(5000);
            parser.setJsonParser(urlConnection.getInputStream());
            results = parser.parse();

            total = results.getTotalResults();

            for (SearchResult result : results.getSearchResults()) {

                if (!jobExists(result)) {
                    result = dataExtractor.appendContactData(result);
                    newJobs.add(result);
                }
            }

            position += limit;
        } while (position < total);

        return newJobs;
    }


    private Boolean jobExists(SearchResult searchResult) {
        List results;
        Map<String, String> params = new HashMap<>();

        params.put("jobKey", searchResult.getJobKey());

        results = searchResultsStore.findWithNamedQuery(SearchResult.GET_BY_JOB_KEY,params,1);

        if (results.size() >= 1) {
            return true;
        }

        return false;
    }

}
