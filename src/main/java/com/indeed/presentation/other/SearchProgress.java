package com.indeed.presentation.other;

public class SearchProgress {

    private Integer current;
    private Integer total;

    public SearchProgress() {
        this.current = 0;
        this.total = 0;
    }

    public SearchProgress(Integer current, Integer total) {
        this.current = current;
        this.total = total;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Float getProgress() {
        float currentFloat = (float) this.current;
        float totalFloat = (float) this.total;

        return (currentFloat / totalFloat * 100);
    }
}
