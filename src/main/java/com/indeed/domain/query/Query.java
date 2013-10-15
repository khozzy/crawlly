package com.indeed.domain.query;

public abstract class Query {
    private String name;
    private String query;
    private String location;
    private Integer daysBack;

    protected Query(String name, String query, String location, Integer howMany) {
        this.name = name;
        this.query = query;
        this.location = location;
        this.daysBack = howMany;
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public String getLocation() {
        return location;
    }

    public Integer getDaysBack() {
        return daysBack;
    }
}
