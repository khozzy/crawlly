package com.indeed.control;

import com.indeed.annotation.Queries;
import com.indeed.domain.query.Query;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Set;

@Singleton
@Startup
public class OfferManager {

    @Inject @Queries
    private Set<Query> queries;

    @Inject
    private Indeed indeed;

    @Schedule(dayOfWeek="Sun", hour="0")
    public void fetchNewOffers() {
        setAllToExpired();

        for (Query query : queries) {
            indeed.search(query);
        }
    }

    @Schedule(dayOfWeek="Sun", hour="12")
    public void sendMailsWithReports() {
        // Raport przyrostowy
        // Raport tylko z nowymi
    }

    private void setAllToExpired() {
        // set all offers in db to expired
        // TODO: Implement
    }
}
