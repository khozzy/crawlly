package com.indeed.presentation.other;

import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.SearchResult;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SearchResultDetailBean implements Serializable {

    private SearchResult searchResult;

    @Inject
    private SearchResultsStore resultsStore;

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public String save() {
        resultsStore.update(searchResult);
        return "index";
    }
}
