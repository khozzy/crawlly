package com.indeed.parser;


import com.indeed.domain.search_result.ParsingSearchResults;
import com.indeed.domain.search_result.SearchResult;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class SearchResultsParserTest {

    private SearchResultsParser searchResultsParser;
    private ParsingSearchResults results;

    @Before
    public void init() throws ParseException {
        searchResultsParser = new SearchResultsParser();
        searchResultsParser.setJsonParser(getClass().getResourceAsStream("/com/indeed/json_results_output.txt"));
        results = searchResultsParser.parse();
    }

    @Test
    public void testIfResultsNotNull() {
        Assert.assertNotNull(results);
    }

    @Test
    public void testIfPaginationFieldsAreSetCorrectly() {
        Assert.assertEquals(new Integer(11), results.getStart());
        Assert.assertEquals(new Integer(20), results.getEnd());
        Assert.assertEquals(new Integer(1), results.getPageNumber());
        Assert.assertEquals(new Integer(1273), results.getTotalResults());
    }

    @Test
    public void testIfAllJobsWereRetrieved() {
        Assert.assertNotNull(results.getSearchResults());
        Assert.assertEquals(10, results.getSearchResults().size());
    }

    @Test
    public void testIfAllMandatoryObjectsPropertiesAreSet() {
        for (SearchResult result : results.getSearchResults()) {
            Assert.assertNotNull(result);
            Assert.assertNotNull(result.getJobTitle());
            Assert.assertNotNull(result.getCompany());
            Assert.assertNotNull(result.getCity());
            Assert.assertNotNull(result.getCountry());
            Assert.assertNotNull(result.getSource());
            Assert.assertNotNull(result.getDate());
            Assert.assertNotNull(result.getUrl());
            Assert.assertNotNull(result.getJobKey());
            Assert.assertNotNull(result.getSponsorded());
            Assert.assertNotNull(result.getExpired());
        }
    }

    @Test
    public void testFirstResultsProperties() {
        SearchResult result = results.getSearchResults().get(0);

        Assert.assertNotNull(result);
        Assert.assertEquals("ASP.NET C# Developer (SaaS , MVC , .NET 4.0)", result.getJobTitle());
        Assert.assertEquals("Vero Solutions", result.getCompany());
        Assert.assertEquals("Dublin", result.getCity());
        Assert.assertEquals("IE", result.getCountry());
        Assert.assertEquals("Vero Solutions", result.getSource());
        Assert.assertEquals("http://ie.indeed.com/viewjob?jk=bc293e9d3712a5fc&qd=VPHN0PdrcxncVkYNgwjvIVK2LRN73PL9MxUv2tSPaJPu0nWO3D2nrxAmX_Tmr0I0lF1tkuxYFf6MdAXsTWg3hx-vHtJLjyfqetGAIQKopQ207x6IsdVxcllsl466nr_GsxdYhmF5_TAlVCWAwBjZUj0s0T4hJ9RnbaOLuy7DdDkUFbb4-txqcxCTPmmG_XDu&indpubnum=5004251131744383&atk=18476rh8e19rh1rk", result.getUrl());
        Assert.assertNotNull(result.getDate());
        Assert.assertEquals(new Double(53.332417), result.getLatitude());
        Assert.assertEquals(new Double(-6.247253), result.getLongtitude());
        Assert.assertEquals("bc293e9d3712a5fc", result.getJobKey());
        Assert.assertEquals(Boolean.FALSE, result.getSponsorded());
        Assert.assertNotSame(Boolean.TRUE, result.getSponsorded());
        Assert.assertEquals(Boolean.FALSE, result.getExpired());
    }

    @Test
    public void testFifthResultProperties() {
        SearchResult result = results.getSearchResults().get(4);

        Assert.assertNotNull(result);
        Assert.assertEquals(".Net Application Developer", result.getJobTitle());
        Assert.assertEquals("Conduit Group Ltd", result.getCompany());
        Assert.assertEquals("", result.getCity());
        Assert.assertEquals("IE", result.getCountry());
        Assert.assertEquals("nijobfinder", result.getSource());
        Assert.assertNotNull(result.getDate());
        Assert.assertEquals("http://ie.indeed.com/viewjob?jk=b98965d32a705bd5&qd=VPHN0PdrcxncVkYNgwjvIVK2LRN73PL9MxUv2tSPaJPu0nWO3D2nrxAmX_Tmr0I0lF1tkuxYFf6MdAXsTWg3hx-vHtJLjyfqetGAIQKopQ207x6IsdVxcllsl466nr_GsxdYhmF5_TAlVCWAwBjZUj0s0T4hJ9RnbaOLuy7DdDkUFbb4-txqcxCTPmmG_XDu&indpubnum=5004251131744383&atk=18476rh8e19rh1rk", result.getUrl());
        Assert.assertEquals("b98965d32a705bd5", result.getJobKey());
        Assert.assertEquals(Boolean.FALSE, result.getSponsorded());
        Assert.assertEquals(Boolean.FALSE, result.getExpired());
    }
}
