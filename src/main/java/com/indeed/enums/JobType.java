package com.indeed.enums;

public enum JobType {

    ALL(""),
    FULLTIME("fulltime"),
    PARTTIME("parttime"),
    CONTRACT("contract"),
    INTERNSHIP("internship"),
    TEMPORARY("temporary");

    private String text;

    private JobType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
