package com.indeed.domain.search_result;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "search_result_email")
public class SearchResultEmail implements Comparable<SearchResultEmail> {

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

    @Override
    public boolean equals(Object obj) {
        SearchResultEmail searchResultEmail = (SearchResultEmail) obj;

        return new EqualsBuilder()
                .append(searchResultEmail.email, email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17,31)
                .append(email)
                .toHashCode();
    }

    @Override
    public int compareTo(SearchResultEmail o) {
        return this.getSearchResult().getCompany().compareTo(o.getSearchResult().getCompany());
    }

    @Override
    public String toString() {
        return email;
    }
}
