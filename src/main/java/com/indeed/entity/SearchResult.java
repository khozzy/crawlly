package com.indeed.entity;

import com.indeed.builder.EmployerWebSiteURIBuilder;
import com.indeed.control.DataExtractor;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.inject.Inject;
import javax.persistence.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "search_result")
public class SearchResult {

    @Inject
    @Transient
    private DataExtractor dataExtractor;

    @Inject
    @Transient
    private EmployerWebSiteURIBuilder employerWebSiteURIBuilder;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "source")
    private String source;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "url")
    private String url;

    @Column(name ="latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longtitude;

    @Column(name = "job_key")
    private String jobKey;

    @Column(name = "sponsored")
    private Boolean sponsorded;

    @Column(name = "expired")
    private Boolean expired;

    @Column(name = "direct_url")
    private String directUrl;

    @OneToMany(mappedBy = "searchResult", cascade = CascadeType.PERSIST)
    private List<SearchResultPhone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "searchResult", cascade = CascadeType.PERSIST)
    private List<SearchResultEmail> emails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirectUrl() {
        return directUrl;
    }

    public void setDirectUrl(String directUrl) {
        this.directUrl = directUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public Boolean getSponsorded() {
        return sponsorded;
    }

    public void setSponsorded(Boolean sponsorded) {
        this.sponsorded = sponsorded;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public List<SearchResultPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<SearchResultPhone> phones) {
        this.phones = phones;
    }

    public List<SearchResultEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<SearchResultEmail> emails) {
        this.emails = emails;
    }


    public void appendContactData() {
        URI jobDetailsSiteURI = null;
        SearchResultEmail resultEmail = new SearchResultEmail();
        SearchResultPhone resultPhone = new SearchResultPhone();

        try {
            jobDetailsSiteURI = employerWebSiteURIBuilder.getEmployerURI(this.getJobKey());
            dataExtractor.setSource(jobDetailsSiteURI.toURL().openStream());
        } catch (URISyntaxException e) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.WARNING, "Cannot create URI for job_key: " + this.getJobKey(), e);
        } catch (IOException e) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.WARNING, "Cannot connect to: " + jobDetailsSiteURI.toString(), e);
        }

        System.out.println("Site: " + jobDetailsSiteURI.toString());

        for (String email :dataExtractor.getEmails()) {
            System.out.println("email = " + email);

            resultEmail.setSearchResult(this);
            resultEmail.setEmail(email);

            this.getEmails().add(resultEmail);
        }

        for (String phone :dataExtractor.getPhoneNumbers()) {
            System.out.println("phone = " + phone);

            resultPhone.setSearchResult(this);
            resultPhone.setPhone(phone);

            this.getPhones().add(resultPhone);
        }

        this.setDirectUrl(jobDetailsSiteURI.toString());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(company)
                .append(jobKey)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        SearchResult searchResult = (SearchResult) obj;

        return new EqualsBuilder()
                .append(searchResult.jobKey, jobKey)
                .isEquals();
    }
}
