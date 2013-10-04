package com.indeed.presentation.other;

import com.indeed.Indeed;
import com.indeed.annotation.Progress;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class SearchBean {
    private String query;
    private String location = "Ireland";
    private Float progress;
    private Boolean isRunning = false;

    @Inject
    private Indeed indeed;

    public void startSearching() {
        indeed.startSearching(query,location);
    }

    public void setRunning() {
        isRunning = true;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Float getProgress() {
        if (progress == null) {
            progress = 0f;
        }

        if (isRunning) {
            startSearching();
        }

        if (progress >= 100f) {
            progress = 100.0f;
            isRunning = false;
        }

        return progress;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }

    private void updateProgress(@Observes @Progress SearchProgress searchProgress) {
        System.out.println("PROGRESS: " + searchProgress.getCurrent() + "/" + searchProgress.getTotal() + " (" + searchProgress.getProgress() + "%)");
        setProgress(searchProgress.getProgress());
    }

    public void onComplete() {
        System.out.println("Process completed...");
    }
}
