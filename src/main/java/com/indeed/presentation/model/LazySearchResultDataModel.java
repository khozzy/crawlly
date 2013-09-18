package com.indeed.presentation.model;

import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.SearchResult;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

public class LazySearchResultDataModel extends LazyDataModel<SearchResult> {

    private int rowCount;
    private int rowIndex;
    private int pageSize;
    private List<SearchResult> datasource;
    private SearchResultsStore resultsStore;

    public LazySearchResultDataModel(SearchResultsStore resultsStore) {
        this.resultsStore = resultsStore;
    }

    @Override
    public List<SearchResult> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        System.out.println("first = [" + first + "], pageSize = [" + pageSize + "], sortField = [" + sortField + "], sortOrder = [" + sortOrder + "], filters = [" + filters + "]");

        datasource = resultsStore.getPaginatedResults(first,pageSize,sortField,sortOrder);
        setRowCount(resultsStore.countTotalRecord(SearchResult.TOTAL));
        return datasource;
    }

    @Override
    public boolean isRowAvailable() {
        if (datasource == null) {
            return false;
        }

        int index = rowIndex % pageSize;
        return index >= 0 && index < datasource.size();
    }

    @Override
    public Object getRowKey(SearchResult searchResult) {
        return searchResult.getId().toString();
    }

    @Override
    public SearchResult getRowData() {

        if(datasource == null)
            return null;

        int index =  rowIndex % pageSize;

        if(index > datasource.size()){
            return null;
        }
        return datasource.get(index);
    }

    @Override
    public SearchResult getRowData(String rowKey) {

        if(datasource == null)
            return null;

        for(SearchResult result : datasource) {
            if(result.getId().toString().equals(rowKey))
                return result;
        }

        return null;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public void setWrappedData(Object list) {
        this.datasource = (List<SearchResult>) list;
    }

    @Override
    public Object getWrappedData() {
        return datasource;
    }
}
