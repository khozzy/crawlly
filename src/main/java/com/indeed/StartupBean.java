package com.indeed;

import com.indeed.control.SearchResultsStore;
import com.indeed.entity.SearchResult;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

@Startup
@Singleton
public class StartupBean {

    @Inject
    Indeed indeed;

    @Inject
    SearchResultsStore resultsStore;

    @PostConstruct
    public void init()  {
        System.out.println("start");

        List<SearchResult> resultsList = null;
        try {
            resultsList = indeed.updateJobs("Java", "Ireland", 25);
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for (SearchResult result : resultsList) {
            resultsStore.save(result);
        }

    }
}
