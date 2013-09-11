package com.crawler;

import com.common.control.Configuration;
import com.common.control.PageStore;
import com.common.entity.SeedPage;
import com.common.entity.SubPage;
import com.crawler.annotation.ComputationResults;
import com.crawler.annotation.ComputationStatus;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class Net {
    private Integer processors = Runtime.getRuntime().availableProcessors();
    private Set<SeedPage> seedPages = Collections.synchronizedSet(new HashSet<SeedPage>());

    @Inject
    PageStore pageStore;

    @Inject
    Configuration configuration;

    @Inject
    Spider spider;

    Status status;

    @PostConstruct
    protected void init() {
        Logger.getLogger(Net.class.getName()).log(Level.INFO, "Net initialized with " + processors + " processors.");
    }

    public Integer getProcessors() {
        return processors;
    }

    public void addSeedPageToCrawl(SeedPage seedPage) {
        seedPages.add(seedPage);  // czy potrzebny mechanizm do zabezpieczenia sie przed wrzuceniem strony 2 razy?
    }

    public Boolean isSeedPageInQueue(SeedPage seedPage) {
        return seedPages.contains(seedPage);
    }

    public void removeSeedPageFromQueue(SeedPage seedPage) {
        seedPages.remove(seedPage);
    }

    public void removeAllSeedPagesFromQueue() {
        seedPages.removeAll(seedPages);
    }

    public Set<SeedPage> getSeedPages() {
        return seedPages;
    }

    public void startCrawling() {
        System.out.println("[NET] Starting crawling " + seedPages.size() + " pages...");

        for (SeedPage seedPage : seedPages) {
            spider.crawl(seedPage.getUrl(), true);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void onCrawlingResult(@Observes(during = TransactionPhase.AFTER_COMPLETION) @ComputationResults CrawlingResults results) {
        System.out.println("[NET] Crawling success.");
        System.out.println("[NET] Analyzing data.");

        SeedPage seedPage = pageStore.getSeedPageByUrl(results.getSeedPage().getUrl());

        pageStore.update(seedPage);

        for (SubPage subPage : results.getSubPages()) {
            subPage.setParent(seedPage);
            pageStore.save(subPage);
        }

        removeSeedPageFromQueue(seedPage);
        this.status.setIdle(true);

        System.out.println("elements in queue: " + seedPages.size());
        System.out.println("[NET] Done ...");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void onCrawlingFailure(@Observes(during = TransactionPhase.AFTER_FAILURE) @ComputationResults CrawlingResults results) {
        System.out.println("[NET] Crawling failure...");
    }

    public void currentStatus(@Observes @ComputationStatus Status status)  {
        if (status == null) {
            this.status.setIdle(true);
        }

        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}
