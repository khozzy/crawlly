package com.indeed.control;

import com.indeed.annotation.Queries;
import com.indeed.builder.ReportBuilder;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Singleton
public class ReportManager {

    @Inject @Queries
    private Set<Query> queries;

    @Inject
    private ReportBuilder reportBuilder;

    @Inject
    private SearchResultsStore searchResultsStore;

    private List<SearchResult> populateReport(Boolean onlyNew) {
        List<SearchResult> searchResults = new ArrayList<>();

        for (Query query : queries) {
            searchResults.addAll(searchResultsStore.findByQuery(query, onlyNew));
        }

        return searchResults;
    }


    public ByteArrayOutputStream generateOverallReport() {
        List<SearchResult> searchResults = populateReport(Boolean.FALSE);

        return reportBuilder.build(searchResults);
    }

    public ByteArrayOutputStream generateNewReport() {
        List<SearchResult> searchResults = populateReport(Boolean.TRUE);

        return reportBuilder.build(searchResults);
    }

    public ByteArrayOutputStream generateOnlyDailyContactsReport() {
        List<SearchResult> searchResults = populateReport(Boolean.TRUE);

        return reportBuilder.onlyContactData(searchResults);
    }
}
