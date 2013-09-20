package com.indeed.presentation.other;


import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.SearchResult;
import com.indeed.presentation.model.LazySearchResultDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class SearchResultTableBean implements Serializable {

    @Inject
    private SearchResultsStore resultsStore;

    @Inject
    private SearchResultDetailBean resultDetailBean;

    private LazyDataModel<SearchResult> lazyModel;
    private SearchResult selectedSearchResult;

    public SearchResultTableBean() {
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazySearchResultDataModel(resultsStore);
    }

    public void orderSelect(SelectEvent event) throws IOException {

        resultDetailBean.setSearchResult(selectedSearchResult);

        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("searchResultDetails.xhtml");

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
