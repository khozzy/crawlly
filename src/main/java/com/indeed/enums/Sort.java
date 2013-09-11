package com.indeed.enums;

public enum Sort {

    RELEVANCE("relevance"), DATE("date");

    private String text;

    private Sort(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
