package com.indeed.presentation.other;


import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.SearchResult;

import com.indeed.entity.SearchResultEmail;
import com.indeed.entity.SearchResultPhone;
import com.indeed.presentation.model.LazySearchResultDataModel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.CustomXLSExporter;
import org.primefaces.component.export.Exporter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Iterator;


@Named
@ViewScoped
public class SearchResultTableBean implements Serializable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final String REPORT_HEADER[] = {"Company", "City", "Fetch date", "Email", "Phone"};
    private static final Integer CELL_WITH_EMAIL = 3;
    private static final Integer CELL_WITH_PHONE = 4;

    @Inject
    private SearchResultsStore resultsStore;

    @Inject
    private SearchResultDetailBean resultDetailBean;

    private LazyDataModel<SearchResult> lazyModel;
    private SearchResult selectedSearchResult;
    private DataTable dataTable;

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

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public void exportPDF(DataTable dataTable, String fileName, Boolean pageOnly) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        Exporter exporter = new CustomXLSExporter();
        exporter.export(context, dataTable, fileName, pageOnly, false, "UTF-8", null, null);
        context.responseComplete();
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook workbook = (HSSFWorkbook) document;
        HSSFSheet sheet = ((HSSFWorkbook) document).getSheetAt(0);

        // Auto-size width
        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}
