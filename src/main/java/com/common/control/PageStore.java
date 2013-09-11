package com.common.control;

import com.common.entity.Page;
import com.common.entity.SeedPage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PageStore {

    @PersistenceContext
    EntityManager em;

    public void save(Page page) {
        this.em.persist(page);
    }

    public void update(Page page) {
        this.em.merge(page);
    }

    public void remove(Page page) {
        this.em.remove(page);
    }

    public List<SeedPage> getAllSeedPages() {
        return (List<SeedPage>) em
                .createQuery("SELECT sp FROM SeedPage sp")
                .getResultList();
    }

    public SeedPage getSeedPageByUrl(String url) {
        return (SeedPage) em
                .createQuery("SELECT sp FROM SeedPage sp WHERE sp.url = :urlGiven")
                .setParameter("urlGiven", url)
                .getSingleResult();
    }

    public Long getSubPagesCountForSeedPage(SeedPage seedPage) {
        return (Long) em
                .createQuery("SELECT COUNT(sp) FROM SubPage sp WHERE sp.parent = :parent")
                .setParameter("parent", seedPage)
                .getSingleResult();
    }

    public void flush() {
        this.em.flush();
    }
}
