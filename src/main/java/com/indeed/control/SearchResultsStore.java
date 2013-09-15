package com.indeed.control;

import com.indeed.entity.SearchResult;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SearchResultsStore {

    @PersistenceContext
    EntityManager em;

    public void save(SearchResult searchResult) {
        this.em.persist(searchResult);
    }

    public void update(SearchResult searchResult) {
        this.em.merge(searchResult);
    }

    public void remove(SearchResult searchResult) {
        this.em.remove(searchResult);
    }

    public List<SearchResult> getAll() {

    }
}
