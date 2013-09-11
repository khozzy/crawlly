package com.crawler;

import com.common.control.UrlManager;
import com.common.entity.ContentInfo;
import com.common.entity.SeedPage;
import com.common.entity.SubPage;
import com.crawler.annotation.ComputationResults;
import com.common.annotation.MaxUrl;
import com.crawler.annotation.ComputationStatus;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class Spider {

    @Inject @ComputationResults
    Event<CrawlingResults> results;

    @Inject @ComputationStatus
    Event<Status> status;

    @Inject @MaxUrl
    private Integer maxUrls;

    @Inject
    private UrlManager urlManager;

    @Asynchronous
    public void crawl(String pageUrl, Boolean isSeedPage) {
        System.out.println("[SPIDER] Thread [ " + Thread.currentThread().getId() + "] Begin to crawl " + pageUrl);

        HashSet crawledList = new HashSet();  // store crawled pages
        LinkedHashSet toCrawlList = new LinkedHashSet(); // store pages to crawl
        HashMap disallowListCache = new HashMap<>(); // we cannot crawl those pages (forbidden by robots.txt)

        Integer counter = 0; // how many pages crawled so far

        // For measuring time execution
        Long startTimeInMillis;
        Long stopTimeInMillis;

        // For storing the results and sending them back to Net
        CrawlingResults crawlingResults = new CrawlingResults();
        SeedPage seedPage = new SeedPage();
        SubPage subPage;
        Set<SubPage> subPages = new HashSet<>();

        toCrawlList.add(pageUrl);

        while (toCrawlList.size() > 0) {

            if (isEmptyOrLimitExceeded(counter, toCrawlList)) {
                crawlingResults.setSubPages(subPages);
                break;
            }

            // Get URL at bottom of list
            String nextPage =  (String) toCrawlList.iterator().next();
            URL verifiedUrl = urlManager.verifyUrl(nextPage);

            toCrawlList.remove(nextPage);

            if ((verifiedUrl == null)) {
                Logger.getLogger(Spider.class.getName()).log(Level.INFO, "URL " + nextPage + " not valid ...");
                continue;
            }

            if (!isRobotAllowed(verifiedUrl, disallowListCache)) {
                Logger.getLogger(Spider.class.getName()).log(Level.INFO, "URL " + nextPage + " blocked by robots.txt");
                continue;
            }

            ContentInfo contentInfo = null;

            try {
                contentInfo = urlManager.retrieveContentInfo(verifiedUrl);
            } catch (Exception e) {
                Logger.getLogger(Spider.class.getName()).log(Level.INFO, "URL " + nextPage + " cannot obtain content info");
                continue;
            }

            if (contentInfo.getContentType() == null) {
                continue;
            }

            if (!contentInfo.getContentType().toLowerCase().contains("text")) {
                continue;
            }

            startTimeInMillis = System.currentTimeMillis();
            String pageContents = urlManager.downloadPage(verifiedUrl);
            stopTimeInMillis = System.currentTimeMillis();

            crawledList.add(verifiedUrl);

            if (isSeedPage) {
                seedPage.setUrl(verifiedUrl.toString());
                seedPage.setContent(pageContents);
                seedPage.setResponseCode(contentInfo.getResponseCode());
                seedPage.setFetchDate(new Timestamp(new Date().getTime()));
                seedPage.setDownloadTime(getExecutionTimeInMillis(startTimeInMillis, stopTimeInMillis));

                crawlingResults.setSeedPage(seedPage);
            } else {
                // analyze page content here
                subPage = new SubPage();
                subPage.setUrl(verifiedUrl.toString());
                subPage.setDownloadTime(getExecutionTimeInMillis(startTimeInMillis, stopTimeInMillis));
                subPage.setFetchDate(new Timestamp(new Date().getTime()));
                subPage.setResponseCode(contentInfo.getResponseCode());
                subPage.setContent(pageContents);

                subPages.add(subPage);
            }

            if (pageContents != null && pageContents.length() > 0) {
                ArrayList<String> links = urlManager.retrieveLinks(verifiedUrl, pageContents, crawledList);
                for (String link : links) {
                    if (!crawledList.contains(urlManager.verifyUrl(link))) {
                        toCrawlList.add(link);
                    }
                }
            }

            status.fire(getCrawlingStatus(verifiedUrl.toString(), crawledList.size(), toCrawlList.size(), maxUrls));

            counter++;
            isSeedPage = false;
        } // while

        crawlingResults.setSubPages(subPages);

        System.out.println("Firing the results...");
        results.fire(crawlingResults);
    }

    private Status getCrawlingStatus(String url, Integer total, Integer left, Integer max) {
        return new Status(url, total,left, max, false);
    }

    private Boolean isEmptyOrLimitExceeded(Integer counter, LinkedHashSet toCrawlList) {
        return ((maxUrls != -1) && ((counter == maxUrls) || toCrawlList.size() == 0));
    }

    private Boolean isRobotAllowed(URL urlToCheck, HashMap disallowListCache)
    {
        String host = urlToCheck.getHost().toLowerCase();
        // Retrieve host's disallow list from cache.
        ArrayList disallowList = (ArrayList) disallowListCache.get(host);

        // If list is not in the cache, download and cache it.
        if (disallowList == null)
        {
            disallowList = new ArrayList();
            try {
                URL robotsFileUrl =
                        new URL("http://" + host + "/robots.txt");

                // Open connection to robot file URL for reading.
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(
                                robotsFileUrl.openStream()));

                // Read robot file, creating list of disallowed paths.
                String line;
                while ((line = reader.readLine()) != null)
                {
                    if (line.toLowerCase().indexOf("Disallow:") == 0) {
                        String disallowPath =  line.toLowerCase().substring("Disallow:".length());
                        // Check disallow path for comments and remove if present.
                        int commentIndex = disallowPath.indexOf("#");
                        if (commentIndex != - 1) {
                            disallowPath =
                                    disallowPath.substring(0, commentIndex);
                        }
                        // Remove leading or trailing spaces from disallow path.
                        disallowPath = disallowPath.trim();
                        // Add disallow path to list.
                        disallowList.add(disallowPath);
                    }
                }
                // Add new disallow list to cache.
                disallowListCache.put(host, disallowList);

            } catch (Exception e)
            {
             /* Assume robot is allowed since an exception
             is thrown if the robot file doesn't exist. */
                return true;
            }
        }
        /* Loop through disallow list to see if
        crawling is allowed for the given URL. */
        String file = urlToCheck.getFile();
        for (int i = 0; i < disallowList.size(); i++)
        {
            String disallow = (String) disallowList.get(i);
            if (file.startsWith(disallow)) {
                return false;
            }
        }
        return true;
    }

    private Long getExecutionTimeInMillis(Long startTime, Long stopTime) {
        return stopTime - startTime;
    }
}
