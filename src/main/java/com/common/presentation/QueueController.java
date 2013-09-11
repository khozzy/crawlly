package com.common.presentation;

import com.common.entity.SeedPage;
import com.crawler.CrawlingResults;
import com.crawler.Net;
import com.crawler.Status;
import com.crawler.annotation.ComputationResults;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.faces.model.DataModel;
import javax.faces.model.ResultSetDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Named
@RequestScoped
public class QueueController implements Serializable {

    @Inject
    Net net;

    public String startCrawling() {
        net.startCrawling();
        return null;
    }

    public Set<SeedPage> getSeedPages() {
        return net.getSeedPages();
    }

    public String addSeedPageToQueue(SeedPage seedPage) {
        net.addSeedPageToCrawl(seedPage);
        return null;
    }

    public String removeSeedPageFromQueue(SeedPage seedPage) {
        net.removeSeedPageFromQueue(seedPage);
        return null;
    }

    public String removeAllSeedPagesFromQueue() {
        net.removeAllSeedPagesFromQueue();
        return null;
    }

    public Boolean isSeedPageInQueue(SeedPage seedPage) {
        return net.isSeedPageInQueue(seedPage);
    }

    public String getCurrentStatus() {
        Status status = net.getStatus();

        if (status == null || status.getIdle()) {
            return "idle";
        }

        return status.getUrl();
    }
}
