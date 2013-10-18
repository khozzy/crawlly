package com.indeed.domain.search_result;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@Entity
@Table(name = "search_result")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "SearchResult.findAll", query = "SELECT sr FROM SearchResult sr"),
        @NamedQuery(name = "SearchResult.countTotal", query = "SELECT COUNT(sr) FROM SearchResult sr"),
        @NamedQuery(name = "SearchResult.getByJobKey", query = "SELECT sr FROM SearchResult sr WHERE sr.jobKey = :jobKey"),
        @NamedQuery(name = "SearchResult.expireAll", query = "UPDATE SearchResult SET expired = true "),
        @NamedQuery(name = "SearchResult.getByQuery", query = "SELECT sr FROM SearchResult sr WHERE sr.queryType = :query"),
        @NamedQuery(name = "SearchResult.getByQueryAndExpired", query = "SELECT sr FROM SearchResult sr WHERE sr.queryType = :query and sr.expired = :expired")
})
public class SearchResult implements Comparable<SearchResult>{

    public final static String ALL = "SearchResult.findAll";
    public final static String TOTAL = "SearchResult.countTotal";
    public final static String GET_BY_JOB_KEY = "SearchResult.getByJobKey";
    public final static String EXPIRE_ALL = "SearchResult.expireAll";
    public final static String GET_BY_QUERY = "SearchResult.getByQuery";
    public final static String GET_BY_QUERY_AND_EXPIRED = "SearchResult.getByQueryAndExpired";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    private Boolean expired = Boolean.FALSE;

    @Column(name = "direct_url")
    private String directUrl;

    @Column(name = "query_type")
    private String queryType;

    @OneToMany(mappedBy = "searchResult", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SearchResultPhone> phones = new HashSet<>();

    @OneToMany(mappedBy = "searchResult", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SearchResultEmail> emails = new HashSet<>();

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

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
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

    public Set<SearchResultPhone> getPhones() {
        return phones;
    }

    public void setPhones(Set<SearchResultPhone> phones) {
        this.phones = phones;
    }

    public Set<SearchResultEmail> getEmails() {
        return emails;
    }

    public void setEmails(Set<SearchResultEmail> emails) {
        this.emails = emails;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
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

    @Override
    public int compareTo(SearchResult searchResult) {
        return this.getDate().compareTo(searchResult.getDate());
    }
}
