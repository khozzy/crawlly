package com.indeed.builder;

import com.indeed.annotation.Queries;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;
import com.indeed.domain.search_result.SearchResultEmail;
import com.indeed.domain.search_result.SearchResultPhone;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ReportBuilder {

    private static final String COLUMNS[] = {"Company", "City", "Fetch date", "Direct URL", "Email", "Phone"};

    @Inject @Queries
    private Set<Query> queries;

    public ByteArrayOutputStream build(List<SearchResult> searchResults) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Iterator<Query> queryIterator = queries.iterator();
        Iterator<SearchResultEmail> searchResultEmailIterator;
        Iterator<SearchResultPhone> searchResultPhoneIterator;

        Integer sheetIndex = 0;
        Integer rowIndex;

        Workbook wb = new HSSFWorkbook();
        List<Sheet> sheets = new ArrayList<>();
        Row row;

        SearchResultEmail resultEmail;
        SearchResultPhone resultPhone;

        try {
            while (queryIterator.hasNext()) {
                Query query = queryIterator.next();
                sheets.add(wb.createSheet(query.getName()));

                rowIndex = 0;

                for (SearchResult searchResult : searchResults) {

                    if (searchResult.getQueryType().equals(query.getName())) {

                        searchResultEmailIterator = searchResult.getEmails().iterator();
                        searchResultPhoneIterator = searchResult.getPhones().iterator();

                        // In case if there are no contact data
                        if (searchResult.getEmails().isEmpty() && searchResult.getPhones().isEmpty()) {
                            row = sheets.get(sheetIndex).createRow(rowIndex++);

                            row.createCell(0).setCellValue(searchResult.getCompany());
                            row.createCell(1).setCellValue(searchResult.getCity());
                            row.createCell(2).setCellValue(searchResult.getDate().toString());
                            row.createCell(3).setCellValue(searchResult.getDirectUrl());
                        }

                        while (searchResultEmailIterator.hasNext()) {
                            resultEmail = searchResultEmailIterator.next();

                            row = sheets.get(sheetIndex).createRow(rowIndex++);

                            row.createCell(0).setCellValue(searchResult.getCompany());
                            row.createCell(1).setCellValue(searchResult.getCity());
                            row.createCell(2).setCellValue(searchResult.getDate().toString());
                            row.createCell(3).setCellValue(searchResult.getDirectUrl());
                            row.createCell(4).setCellValue(resultEmail.getEmail());
                        }

                        while (searchResultPhoneIterator.hasNext()) {
                            resultPhone = searchResultPhoneIterator.next();

                            row = sheets.get(sheetIndex).createRow(rowIndex++);

                            row.createCell(0).setCellValue(searchResult.getCompany());
                            row.createCell(1).setCellValue(searchResult.getCity());
                            row.createCell(2).setCellValue(searchResult.getDate().toString());
                            row.createCell(3).setCellValue(searchResult.getDirectUrl());
                            row.createCell(5).setCellValue(resultPhone.getPhone());
                        }
                    }
                }

                // Auto size column width
                for (int col_i = 0; col_i < COLUMNS.length; col_i++) {
                    sheets.get(sheetIndex).autoSizeColumn(col_i);
                }

                sheetIndex++;
            }

            wb.write(baos);
        } catch (IOException e) {
            Logger.getLogger(ReportBuilder.class.getName()).log(Level.WARNING, "Cannot save report", e);
        }

        return baos;
    }
}
