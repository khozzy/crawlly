package com.indeed;

import com.indeed.control.SearchResultsStore;
import com.indeed.entity.ParsingSearchResults;
import com.indeed.entity.SearchResult;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SearchResults {

    @Inject
    SearchResultsStore searchResultsStore;

    @Inject
    SearchURIBuilder searchURIBuilder;

    @Inject
    SearchResultsParser parser;

    public List<SearchResult> getAllResults(String query, String location, Integer limit) throws URISyntaxException, IOException, ParseException {
        List<SearchResult> allResults = new ArrayList<>();

        Integer position = 0;
        Integer total;
        URI uri;
        ParsingSearchResults results;

        do {
            uri = searchURIBuilder.getURIForPagination(query,location, position, limit);
            parser.setJsonParser(uri.toURL().openStream());
            results = parser.parse();

            total = results.getTotalResults();
            allResults.addAll(results.getSearchResults());

            position += limit;
        } while (position < total);

        return allResults;
    }

}
