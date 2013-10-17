package com.indeed.control;

import com.indeed.annotation.Queries;
import com.indeed.control.store.SearchResultsStore;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Set;

@Singleton
public class OfferManager {

    @Inject @Queries
    private Set<Query> queries;

    @Inject
    private Indeed indeed;

    @Inject
    private SearchResultsStore searchResultsStore;

    @Schedule(minute = "*", hour = "*")
    public void fetchNewOffers() {
        System.out.println("Scheduled... Fetching new offers");

        setAllToExpired();

        for (Query query : queries) {
            System.out.println("Searching for " + query.getName());
            indeed.search(query);
        }

        System.out.println("Finished");
    }

    @Schedule(dayOfWeek="Sun", hour="12")
    public void sendMailsWithReports() {

    }

    private void setAllToExpired() {
        System.out.println("Setting all as expired ...");
        searchResultsStore.executeNamedQuery(SearchResult.EXPIRE_ALL);
    }
}
