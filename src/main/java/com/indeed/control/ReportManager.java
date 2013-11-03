package com.indeed.control;

import com.indeed.annotation.Queries;
import com.indeed.builder.ReportBuilder;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;
import com.indeed.domain.search_result.SearchResultEmail;
import com.indeed.domain.search_result.SearchResultPhone;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.util.*;

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

    public ByteArrayOutputStream generateFullContactsReport() {
        List<SearchResult> searchResults = populateReport(Boolean.FALSE);

        Set<SearchResultEmail> uniqueEmails = new HashSet<>();
        Set<SearchResultPhone> uniquePhones = new HashSet<>();

        for (SearchResult searchResult : searchResults) {
            for (SearchResultEmail email : searchResult.getEmails()) {
                uniqueEmails.add(email);
            }

            for (SearchResultPhone phone : searchResult.getPhones()) {
                uniquePhones.add(phone);
            }
        }

        return reportBuilder.onlyContactData(uniqueEmails, uniquePhones);
    }

}
