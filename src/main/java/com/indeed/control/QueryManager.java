package com.indeed.control;

import com.indeed.annotation.Queries;
import com.indeed.domain.query.JavaQuery;
import com.indeed.domain.query.PythonQuery;
import com.indeed.domain.query.Query;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class QueryManager {

    protected Set<Query> queries;

    @PostConstruct
    public void init() {

        queries = new HashSet<>();

        queries.add(new JavaQuery(10));
        queries.add(new PythonQuery(10));
    }

    @Produces @Queries
    public Set<Query> getQueries() {
        return this.queries;
    }
}
