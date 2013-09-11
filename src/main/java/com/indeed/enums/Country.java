package com.indeed.enums;

public enum Country {

    UNITED_STATES("us"),
    IRELAND("ie"),
    POLAND("pl");

    private String text;

    private Country(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
