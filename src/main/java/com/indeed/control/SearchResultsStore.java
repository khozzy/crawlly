package com.indeed.control;

import com.indeed.entity.SearchResult;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public SearchResult getByJobKey(String jobKey) {
        try {
            return (SearchResult) em
                    .createQuery("SELECT sr FROM SearchResult sr WHERE sr.jobKey = :jobKey")
                    .setParameter("jobKey", jobKey)
                    .getSingleResult();  }
        catch (NoResultException e) {
            return null;
        }
    }

    public List<SearchResult> getAll() {
        return (List<SearchResult>) em
                .createQuery("SELECT sr FROM SearchResult sr ORDER BY sr.date DESC")
                .setMaxResults(50)
                .getResultList();
    }
}
