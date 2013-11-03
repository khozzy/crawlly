package com.indeed.domain.search_result;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "search_result_phone")
public class SearchResultPhone implements Comparable<SearchResultPhone> {

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
    public boolean equals(Object obj) {
        SearchResultPhone searchResultPhone = (SearchResultPhone) obj;

        return new EqualsBuilder()
                .append(searchResultPhone.phone, phone)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17,31)
                .append(phone)
                .toHashCode();
    }

    @Override
    public int compareTo(SearchResultPhone o) {
        return this.getSearchResult().getCompany().compareTo(o.getSearchResult().getCompany());
    }

    @Override
    public String toString() {
        return phone;
    }
}
