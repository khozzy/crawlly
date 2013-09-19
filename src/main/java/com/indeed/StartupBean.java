package com.indeed;

import com.indeed.control.store.SearchResultsStore;
import com.indeed.entity.SearchResult;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

@Singleton
@Startup
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
            resultsList = indeed.updateJobs("Chemistry or Biotechnology or Process Engineer or Engineering Validation or Biology or Project Assurance or Laboratory or BioPharma or Chemical", "Ireland", 25);
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for (SearchResult result : resultsList) {
            resultsStore.create(result);
        }

    }
}
