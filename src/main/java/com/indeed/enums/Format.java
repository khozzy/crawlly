package com.indeed.enums;

public enum Format {

    JSON("json"), XML("xml");

    private String text;

    private Format(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
