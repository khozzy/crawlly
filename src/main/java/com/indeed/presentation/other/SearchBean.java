package com.indeed.presentation.other;

import com.indeed.Indeed;
import com.indeed.control.Messages;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.SearchResult;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class SearchBean {

    private static final Integer limit = 25;
    private Integer currentIndex;
    private Integer totalSearchResults;

    private String query;
    private String location = "Ireland";

    List<SearchResult> newResults;

    @Inject
    private Indeed indeed;

    @Inject
    private SearchResultsStore resultsStore;

    public void startSearching() {
        System.out.println("Started searching ...");

        try {
            totalSearchResults = indeed.getTotalSearchResults(query, location, limit);
            newResults = indeed.getNewJobs(query, location, limit);

            for (SearchResult result : newResults) {
                resultsStore.create(result);
            }

        } catch (URISyntaxException | IOException | ParseException e) {
            Logger.getLogger(SearchBean.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        System.out.println("Finished");
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
