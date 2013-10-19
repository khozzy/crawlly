package com.indeed.control;

import com.indeed.annotation.Queries;
import com.indeed.domain.query.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Singleton
public class QueryManager {

    protected Set<Query> queries;

    @PostConstruct
    public void init() {
        queries = new HashSet<>();

        queries.add(new Java_Query(10));
        queries.add(new Python_Query(10));
        queries.add(new DotNET_Query(10));
        queries.add(new ANSI_C_Query(10));
        queries.add(new ASP_Query(10));
        queries.add(new Software_Query(10));
        queries.add(new Developer_Query(10));
        queries.add(new Ruby_Query(10));
        queries.add(new Oracle_Query(10));
        queries.add(new CPP_Query(10));
        queries.add(new Assembler_Query(10));
        queries.add(new CSharp_Query(10));
        queries.add(new ABAP_Query(10));
        queries.add(new SAP_Query(10));
        queries.add(new JavaScript_Query(10));
        queries.add(new Delphi_Query(10));
        queries.add(new Perl_Query(10));
        queries.add(new PHP_Query(10));
    }

    @Produces @Queries
    public Set<Query> getQueries() {
        return this.queries;
    }
}
