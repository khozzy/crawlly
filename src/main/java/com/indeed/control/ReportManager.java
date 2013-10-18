package com.indeed.control;


import com.indeed.annotation.Queries;
import com.indeed.builder.ReportBuilder;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
@Singleton
public class ReportManager {

    @Inject @Queries
    private Set<Query> queries;

    @Inject
    private ReportBuilder reportBuilder;

    @Inject
    private SearchResultsStore searchResultsStore;

    private Boolean onlyNew = Boolean.FALSE;

    private FileOutputStream generateFile() {
        List<SearchResult> searchResults = new ArrayList<>();

        for (Query query : queries) {
            searchResults.addAll(searchResultsStore.findByQuery(query, onlyNew));
        }

        reportBuilder.build(searchResults);

        return null;
    }

    public FileOutputStream generateOverallReport() {
        onlyNew = Boolean.FALSE;
        System.out.println("Generating overall report");

        return generateFile();
    }

    public FileOutputStream generateNewReport() {
        onlyNew = Boolean.TRUE;
        System.out.println("Generating only new report");

        return generateFile();
    }
}
