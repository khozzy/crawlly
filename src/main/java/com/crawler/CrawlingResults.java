package com.crawler;

import com.common.entity.SeedPage;
import com.common.entity.SubPage;

import java.util.Set;

public class CrawlingResults {
    private SeedPage seedPage;
    private Set<SubPage> subPages;

    public SeedPage getSeedPage() {
        return seedPage;
    }

    public void setSeedPage(SeedPage seedPage) {
        this.seedPage = seedPage;
    }

    public Set<SubPage> getSubPages() {
        return subPages;
    }

    public void setSubPages(Set<SubPage> subPages) {
        this.subPages = subPages;
    }
}
