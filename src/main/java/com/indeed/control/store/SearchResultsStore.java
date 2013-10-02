package com.indeed.control.store;

import com.indeed.entity.SearchResult;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class SearchResultsStore extends Store<SearchResult> {

    public SearchResultsStore() {
        super(SearchResult.class);
    }
}
