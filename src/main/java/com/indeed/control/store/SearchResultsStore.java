package com.indeed.control.store;

import com.indeed.domain.query.Query;
import com.indeed.domain.search_result.SearchResult;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class SearchResultsStore extends Store<SearchResult> {

    public SearchResultsStore() {
        super(SearchResult.class);
    }

    public List<SearchResult> findByQuery(Query query, Boolean onlyNew) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("query", query.getName());

        // All results
        if (onlyNew == Boolean.FALSE) {
            return (List<SearchResult>) findWithNamedQuery(SearchResult.GET_BY_QUERY, parameters);
        }

        parameters.put("expired", Boolean.FALSE);

        return (List<SearchResult>) findWithNamedQuery(SearchResult.GET_BY_QUERY_AND_EXPIRED, parameters);
    }
}
