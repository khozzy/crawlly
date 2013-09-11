package com.common.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content")
    private String content; // tu będzie docelowo chowany hash strony, nie cała treść

    @Column(name = "fetch_date")
    private Timestamp fetchDate;

    @Column(name = "download_time")
    private Long downloadTime;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;

    @Version
    @Column(name = "version")
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getFetchDate() {
        return fetchDate;
    }

    public void setFetchDate(Timestamp fetchDate) {
        this.fetchDate = fetchDate;
    }

    public Long getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Long downloadTime) {
        this.downloadTime = downloadTime;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object obj) {
        Page page = (Page) obj;

        return new EqualsBuilder()
                .append(page.id, id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(url)
                .append(content)
                .toHashCode();
    }

    @Override
    public String toString() {
        return this.url;
    }
}
