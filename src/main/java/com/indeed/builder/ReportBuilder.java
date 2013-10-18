package com.indeed.builder;

import com.indeed.domain.search_result.SearchResult;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ReportBuilder {

    public void build(List<SearchResult> searchResults) {
        System.out.println("Inside ReportBuilder");
        System.out.println("Got " + searchResults.size() + " elements.");
    }
}
