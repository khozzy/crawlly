package com.indeed;

import com.indeed.enums.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import javax.ejb.Stateless;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SearchURIBuilder {

    private final String protocol = "http";
    private final String host = "api.indeed.com";
    private final String path = "/ads/apisearch";
    private final Long publisherId = 5004251131744383L;
    private final Integer version = 2;
    private final Format format = Format.JSON;
    private final Sort sort = Sort.RELEVANCE;
    private final Integer radius = 25; // distance from search location
    private final SiteType siteType = SiteType.ALL;
    private final JobType jobType = JobType.ALL;
    private final Integer highlight = 0; // Setting this value to 1 will bold terms in the snippet that are also present in q
    private final Integer filter = 1; // filter duplicate results
    private final Integer latlong = 1; // latitude and longitude information for each job result
    private final Country country = Country.IRELAND;
    private final String channel = ""; // Channel Name: Group API requests to a specific channel
    private final String userIp = "1.2.3.4";
    private final String userAgent = "Java/1.7.0_25";

    private List<NameValuePair> getBasicParameters() {
        List<NameValuePair> qparams = new ArrayList<>();

        qparams.add(new BasicNameValuePair("publisher", String.valueOf(publisherId)));
        qparams.add(new BasicNameValuePair("v", String.valueOf(version)));
        qparams.add(new BasicNameValuePair("format", format.toString()));
        qparams.add(new BasicNameValuePair("sort", sort.toString()));
        qparams.add(new BasicNameValuePair("radius", String.valueOf(radius)));
        qparams.add(new BasicNameValuePair("st", siteType.toString()));
        qparams.add(new BasicNameValuePair("jt", jobType.toString()));
        qparams.add(new BasicNameValuePair("highlight", String.valueOf(highlight)));
        qparams.add(new BasicNameValuePair("filter", String.valueOf(filter)));
        qparams.add(new BasicNameValuePair("latlong", String.valueOf(latlong)));
        qparams.add(new BasicNameValuePair("co", country.toString()));
        qparams.add(new BasicNameValuePair("chnl", channel));
        qparams.add(new BasicNameValuePair("userip", userIp));
        qparams.add(new BasicNameValuePair("useragent", userAgent));

        return qparams;
    }

    private List<NameValuePair> setParametersForPagination(Integer start, Integer limit) {
        List<NameValuePair> qparams = getBasicParameters();

        qparams.add(new BasicNameValuePair("start", String.valueOf(start)));
        qparams.add(new BasicNameValuePair("limit", String.valueOf(limit)));

        return qparams;
    }

    private List<NameValuePair> setParametersForFromage(Integer start, Integer limit, Integer daysBack) {
        List<NameValuePair> qparams = getBasicParameters();

        qparams.add(new BasicNameValuePair("start", String.valueOf(start)));
        qparams.add(new BasicNameValuePair("limit", String.valueOf(limit)));
        qparams.add(new BasicNameValuePair("fromage", String.valueOf(daysBack)));

        return qparams;
    }

    private List<NameValuePair> setQueryAndLocation(String query, String location) {
        List<NameValuePair> qparams = new ArrayList<>();

        qparams.add(new BasicNameValuePair("q", String.valueOf(query)));
        qparams.add(new BasicNameValuePair("l", String.valueOf(location)));

        return qparams;
    }

    public URI getURIForPagination(String query, String location, Integer start, Integer limit) throws URISyntaxException {
        List<NameValuePair> qparams = setParametersForPagination(start,limit);
        qparams.addAll(setQueryAndLocation(query,location));

        return URIUtils.createURI(protocol, host, -1, path, URLEncodedUtils.format(qparams, "UTF-8"), null);
    }

    public URI getURIForFromage(String query, String location, Integer start, Integer limit, Integer daysBack) throws URISyntaxException {
        List<NameValuePair> qparams = setParametersForFromage(start, limit, daysBack);
        qparams.addAll(setQueryAndLocation(query,location));

        return URIUtils.createURI(protocol, host, -1, path, URLEncodedUtils.format(qparams, "UTF-8"), null);
    }
}
