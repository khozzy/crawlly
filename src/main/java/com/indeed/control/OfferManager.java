package com.indeed.control;

import com.indeed.annotation.Queries;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;

import javax.ejb.AccessTimeout;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.FileOutputStream;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class OfferManager {

    @Inject @Queries
    private Set<Query> queries;

    @Inject
    private Indeed indeed;

    @Inject
    private SearchResultsStore searchResultsStore;

    @Inject
    private ReportManager reportManager;

//    @Schedule(minute = "*", hour = "*")
//    @Schedule(hour = "23/12", minute = "59")
    @AccessTimeout(value = 0) // concurrent access is not permitted
    public void fetchNewOffers() {
        Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Fetching new offers started");

        setAllToExpired();

        for (Query query : queries) {
            Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Searching for " + query.getName());
            indeed.search(query);
        }

        Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Fetching new offers finished");
    }

    @Schedule(dayOfWeek="Sun", hour="12")
    public void sendMailsWithReports() {
        Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Generating reports started");

        FileOutputStream newRecordsXLSFile = reportManager.generateNewReport();
        FileOutputStream allRecordsXLSFile = reportManager.generateOverallReport();

        Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Generating reports finished");

    }

    private void setAllToExpired() {
        Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Setting all as expired ...");

        searchResultsStore.executeNamedQuery(SearchResult.EXPIRE_ALL);
    }

}
