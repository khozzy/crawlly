package com.indeed.control;

import com.indeed.annotation.Queries;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.domain.ReportAttachment;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;
import org.apache.commons.lang.StringUtils;

import javax.ejb.AccessTimeout;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class OfferManager {

    private final String MAIL_RECIPIENTS = "khozzy@gmail.com";

    @Inject @Queries
    private Set<Query> queries;

    @Inject
    private Indeed indeed;

    @Inject
    private SearchResultsStore searchResultsStore;

    @Inject
    private ReportManager reportManager;

    @Inject
    private Mail mailer;

    @Schedule(hour = "9", minute = "20", timezone = "CET")
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

    @Schedule(hour="8", minute = "0", timezone = "CET")
    public void sendMailsWithReports() {
        Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Generating reports started");

        ReportAttachment newOffersReport = new ReportAttachment(getDateFormatted() + "_New_Full.xls", "application/vnd.ms-excel", reportManager.generateNewReport().toByteArray());
        ReportAttachment allOffersReport = new ReportAttachment(getDateFormatted() + "_All_Full.xls", "application/vnd.ms-excel", reportManager.generateOverallReport().toByteArray());

        try {
            mailer.send(MAIL_RECIPIENTS, "Job offers reports", createMailBodyText(), newOffersReport, allOffersReport);
        } catch (IOException e) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.WARNING, "Cannot send mail with attachments", e);
        }

        Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Generating reports finished");
    }

    private void setAllToExpired() {
        Logger.getLogger(OfferManager.class.getName()).log(Level.INFO, "Setting all as expired ...");
        searchResultsStore.executeNamedQuery(SearchResult.EXPIRE_ALL);
    }

    private String getDateFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        return sdf.format(new Date());
    }

    private String createMailBodyText() {
        StringBuilder sb = new StringBuilder();
        List<String> queryNames = new ArrayList<>();

        for (Query query :queries) {
            queryNames.add(query.getName());
        }

        sb.append("Report includes following queries: ");
        sb.append(StringUtils.join(queryNames.toArray(), ", "));

        return sb.toString();
    }

}
