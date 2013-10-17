package com.indeed.control;


import com.indeed.annotation.Queries;
import com.indeed.builder.ReportBuilder;
import com.indeed.domain.query.Query;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.FileOutputStream;
import java.util.Set;

@Singleton
public class ReportManager {

    @Inject @Queries
    private Set<Query> queries;

    @Inject
    private ReportBuilder reportBuilder;

    private Boolean onlyNew = Boolean.FALSE;

    private FileOutputStream generateFile() {
        // TODO: reportBuilder.build

        return null;
    }

    public FileOutputStream generateOverallReport() {
        onlyNew = Boolean.FALSE;

        return generateFile();
    }

    public FileOutputStream generateNewReport() {
        onlyNew = Boolean.TRUE;

        return generateFile();
    }
}
