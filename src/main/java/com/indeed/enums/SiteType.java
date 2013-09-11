package com.indeed.enums;

public enum SiteType {

    ALL(""), JOB_BOARDS("jobsite"), DIRECT_EMPLOYER("employer");

    private String text;

    private SiteType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
