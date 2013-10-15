package com.indeed.control;

import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.indeed.builder.EmployerWebSiteURIBuilder;
import com.indeed.domain.search_result.SearchResult;
import com.indeed.domain.search_result.SearchResultEmail;
import com.indeed.domain.search_result.SearchResultPhone;
import org.apache.commons.io.IOUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class DataExtractor {

    @Inject
    private EmployerWebSiteURIBuilder employerWebSiteURIBuilder;

    private final static Pattern emailPattern = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
//    private final static Pattern phoneNumberPattern = Pattern.compile("((\\+)?[1-9]{1,4})?([-\\s\\./])?((\\(\\d{1,4}\\))|\\d{1,4})(([-\\s\\./])?[0-9]{1,6}){2,6}(\\s?(ext|x)\\s?[0-9]{1,6})?");

    private String source;

    public void setSource(String source) {
        this.source = source;
    }

    public void setSource(InputStream inputStream) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer);
        this.source = writer.toString();
    }

    public Set<String> getEmails() {
        Set<String> emails = new HashSet<>();

        Matcher m = emailPattern.matcher(source);

        while (m.find()) {
            emails.add(m.group());
        }

        return emails;
    }

    public Set<String> getPhoneNumbers() {
        Set<String> phones = new HashSet<>();

        // Google number finder
        PhoneNumberUtil util = PhoneNumberUtil.getInstance();

        Iterator<PhoneNumberMatch> iterator = util.findNumbers(source,  null).iterator();

        while (iterator.hasNext()) {
            phones.add(iterator.next().rawString());
        }

//        // Irish phone number validator
//        Matcher m = phoneNumberPattern.matcher(source);
//
//        while (m.find()) {
//            phones.add(m.group());
//        }

        return phones;
    }

    public SearchResult appendContactData(SearchResult result) {
        URI jobDetailsSiteURI = null;
        SearchResultEmail resultEmail;
        SearchResultPhone resultPhone;


        try {
            jobDetailsSiteURI = employerWebSiteURIBuilder.getEmployerURI(result.getJobKey());
            setSource(jobDetailsSiteURI.toURL().openStream());
        } catch (URISyntaxException e) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.WARNING, "Cannot create URI for job_key: " + result.getJobKey(), e);
        } catch (IOException e) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.WARNING, "Cannot connect to: " + jobDetailsSiteURI.toString(), e);
        }

        System.out.println("Site: " + jobDetailsSiteURI.toString());

        for (String email : getEmails()) {
            resultEmail = new SearchResultEmail();

            resultEmail.setSearchResult(result);
            resultEmail.setEmail(email);

            result.getEmails().add(resultEmail);
        }

        for (String phone : getPhoneNumbers()) {
            resultPhone = new SearchResultPhone();

            resultPhone.setSearchResult(result);
            resultPhone.setPhone(phone);

            result.getPhones().add(resultPhone);
        }

        result.setDirectUrl(jobDetailsSiteURI.toString());

        return result;
    }


}
