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

    private Boolean onlyNew = Boolean.FALSE;

    private ByteArrayOutputStream generateFile() {
        List<SearchResult> searchResults = new ArrayList<>();

        for (Query query : queries) {
            searchResults.addAll(searchResultsStore.findByQuery(query, onlyNew));
        }

        return reportBuilder.build(searchResults);
    }

    public ByteArrayOutputStream generateOverallReport() {
        onlyNew = Boolean.FALSE;
        return generateFile();
    }

    public ByteArrayOutputStream generateNewReport() {
        onlyNew = Boolean.TRUE;
        return generateFile();
    }
}
