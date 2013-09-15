package com.indeed;

import com.indeed.control.SearchResultsStore;
import com.indeed.entity.ParsingSearchResults;
import com.indeed.entity.SearchResult;
import com.indeed.entity.SearchResultEmail;
import com.indeed.entity.SearchResultPhone;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class SearchResults {

    @Inject
    private SearchResultsStore searchResultsStore;

    @Inject
    private SearchURIBuilder searchURIBuilder;

    @Inject
    private EmployerWebSiteURIBuilder employerWebSiteURIBuilder;

    @Inject
    private DataExtractor dataExtractor;

    @Inject
    private SearchResultsParser parser;

    public List<SearchResult> getAllResults(String query, String location, Integer limit) throws URISyntaxException, IOException, ParseException {
        List<SearchResult> allResults = new ArrayList<>();

        Integer position = 0;
        Integer total;
        URI uri;
        ParsingSearchResults results;

        do {
            uri = searchURIBuilder.getURIForPagination(query,location, position, limit);
            parser.setJsonParser(uri.toURL().openStream());
            results = parser.parse();

            total = results.getTotalResults();

            for (SearchResult result : results.getSearchResults()) {
                result = appendContactData(result);

                allResults.add(result);
            }

            position += limit;
        } while (position < total);

        return allResults;
    }

    protected SearchResult appendContactData(SearchResult result)  {

        URI jobDetailsSiteURI = null;
        SearchResultEmail resultEmail = new SearchResultEmail();
        SearchResultPhone resultPhone = new SearchResultPhone();

        try {
            jobDetailsSiteURI = employerWebSiteURIBuilder.getEmployerURI(result.getJobKey());
            dataExtractor.setSource(jobDetailsSiteURI.toURL().openStream());
        } catch (URISyntaxException e) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.WARNING, "Cannot create URI for job_key: " +result.getJobKey(), e);
        } catch (IOException e) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.WARNING, "Cannot connect to: " + jobDetailsSiteURI.toString(), e);
        }

        System.out.println("Site: " + jobDetailsSiteURI.toString());

        for (String email :dataExtractor.getEmails()) {
            System.out.println("email = " + email);

            resultEmail.setSearchResult(result);
            resultEmail.setEmail(email);

            result.getEmails().add(resultEmail);
        }

        for (String phone :dataExtractor.getPhoneNumbers()) {
            System.out.println("phone = " + phone);

            resultPhone.setSearchResult(result);
            resultPhone.setPhone(phone);

            result.getPhones().add(resultPhone);
        }

        result.setDirectUrl(jobDetailsSiteURI.toString());
        return result;
    }

}
