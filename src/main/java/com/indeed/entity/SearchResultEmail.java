package com.indeed.entity;

import javax.persistence.*;

@Entity
@Table(name = "search_result_email")
public class SearchResultEmail {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "search_result_id")
    private SearchResult searchResult;

    public SearchResultEmail() {
    }

    public SearchResultEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }
}
