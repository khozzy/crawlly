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
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

    public List<SearchResult> updateJobs(String query, String location, Integer limit) throws URISyntaxException, IOException, ParseException {
        List<SearchResult> newJobs = new ArrayList<>();

        Integer position = 0;
        Integer total;
        URI uri;
        ParsingSearchResults results;

        do {
            uri = searchURIBuilder.getURIForPagination(query,location, position, limit);
            parser.setJsonParser(uri.toURL().openStream());
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
        return false;
//        return (searchResultsStore.getByJobKey(searchResult.getJobKey()) != null);
    }
}
