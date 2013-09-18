package com.indeed.control.store;

import com.indeed.entity.SearchResult;

import javax.ejb.Stateless;

@Stateless
public class SearchResultsStore extends Store<SearchResult> {

    public SearchResultsStore() {
        super(SearchResult.class);
    }
}
