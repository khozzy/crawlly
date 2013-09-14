package com.indeed.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "search_result")
public class SearchResult {

    @Id
    @GeneratedValue
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
