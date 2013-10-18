package com.indeed.builder;

import com.indeed.annotation.Queries;
import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ReportBuilder {

    private static final String COLUMNS[] = {"Company", "Job title"};

    @Inject @Queries
    private Set<Query> queries;

    public void build(List<SearchResult> searchResults) {
        System.out.println("Inside ReportBuilder");

        Iterator<Query> queryIterator = queries.iterator();
        Integer sheetIndex = 0;
        Integer rowIndex;

        Workbook wb = new HSSFWorkbook();
        List<Sheet> sheets = new ArrayList<>();
        Row row;

        try {
            FileOutputStream fileOut = new FileOutputStream("/tmp/report.xls");

            while (queryIterator.hasNext()) {
                Query query = queryIterator.next();
                sheets.add(wb.createSheet(query.getName()));

                for (int col_i = 0; col_i < COLUMNS.length; col_i++) {
                    row = sheets.get(sheetIndex).createRow(0);
                    System.out.println("Setting value for " + col_i + " cell: " + COLUMNS[col_i]);
                    row.createCell(col_i).setCellValue(COLUMNS[col_i]);
                }

                rowIndex = 1;

                for (SearchResult searchResult : searchResults) {

                    if (searchResult.getQueryType().equals(query.getName())) {

                        row = sheets.get(sheetIndex).createRow(rowIndex);

                        row.createCell(0).setCellValue(searchResult.getCompany());
                        row.createCell(1).setCellValue(searchResult.getJobTitle());

                        rowIndex++;
                    }
                }

                // Auto size column width
                for (int col_i = 0; col_i < COLUMNS.length; col_i++) {
                    sheets.get(sheetIndex).autoSizeColumn(col_i);
                }

                sheetIndex++;
            }

            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            Logger.getLogger(ReportBuilder.class.getName()).log(Level.WARNING, "Cannot save report", e);
        }


        System.out.println("Report saved");
    }
}
