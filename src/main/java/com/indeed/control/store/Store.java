package com.indeed.control.store;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Store<T> implements Serializable{

    @PersistenceContext
    private EntityManager em;

    public Store() {
    }

    private Class<T> type;

    public Store(Class<T> type) {
        this.type = type;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    public T find(Object id) {
        return this.em.find(this.type, id);
    }

    public void delete(Object id) {
        Object ref = this.em.getReference(this.type, id);
        this.em.remove(ref);
    }

    public boolean deleteItems(T[] items) {
        for (T item : items) {
            em.remove(em.merge(item));
        }
        return true;
    }

    public T update(T t) {
        return (T) this.em.merge(t);
    }

    public List findWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<T> findByNativeQuery(String sql) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    public int countTotalRecord(String namedQueryName) {
        Query query = em.createNamedQuery(namedQueryName);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    public List findWithNamedQuery(String namedQueryName, int start, int end) {
        Query query = this.em.createNamedQuery(namedQueryName);
        query.setMaxResults(end - start);
        query.setFirstResult(start);
        return query.getResultList();
    }

    public List getPaginatedResults(int first, int pageSize, String sortField, SortOrder sortOrder) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        if (sortOrder.equals(SortOrder.ASCENDING)) {
            query.orderBy(builder.asc(root.get(sortField)));
        } else {
            query.orderBy(builder.desc(root.get(sortField)));
        }

        return this.em
                .createQuery(query)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public void executeNamedQuery(String namedQueryName) {
        Query query = this.em.createNamedQuery(namedQueryName);

        query.executeUpdate();
    }
}

