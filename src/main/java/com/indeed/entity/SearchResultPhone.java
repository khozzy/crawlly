package com.indeed.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "search_result_phone")
public class SearchResultPhone {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "search_result_id")
    private SearchResult searchResult;

    public SearchResultPhone() {
    }

    public SearchResultPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String email) {
        this.phone = email;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    @Override
    public String toString() {
        return phone;
    }
}
