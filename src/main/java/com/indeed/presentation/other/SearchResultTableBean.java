package com.indeed.presentation.other;


import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.SearchResult;
import com.indeed.presentation.model.LazySearchResultDataModel;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class SearchResultTableBean implements Serializable {

    @Inject
    private SearchResultsStore resultsStore;

    private LazyDataModel<SearchResult> lazyModel;
    private SearchResult selectedSearchResult;

    public SearchResultTableBean() {
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazySearchResultDataModel(resultsStore);
    }

    public LazyDataModel<SearchResult> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<SearchResult> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public SearchResult getSelectedSearchResult() {
        return selectedSearchResult;
    }

    public void setSelectedSearchResult(SearchResult selectedSearchResult) {
        this.selectedSearchResult = selectedSearchResult;
    }
}
