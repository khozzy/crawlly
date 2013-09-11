package com.crawler;

public class Status {
    private String url;
    private Integer total;
    private Integer left;
    private Integer max;
    private Boolean idle;

    public Status(String url, Integer total, Integer left, Integer max, Boolean idle) {
        this.url = url;
        this.total = total;
        this.left = left;
        this.max = max;
        this.idle = idle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getIdle() {
        return idle;
    }

    public void setIdle(Boolean idle) {
        this.idle = idle;
    }
}
