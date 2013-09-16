package com.indeed.builder;

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
public class EmployerWebSiteURIBuilder {

    private final String protocol = "http";
    private final String host = "ie.indeed.com";
    private final String path = "/rc/clk";

    private List<NameValuePair> getJobKeyParameter(String jobKey) {
        List<NameValuePair> qparams = new ArrayList<>();
        qparams.add(new BasicNameValuePair("jk", String.valueOf(jobKey)));
        return qparams;
    }

    public URI getEmployerURI(String jobkey) throws URISyntaxException {
        return URIUtils.createURI(protocol, host, -1, path, URLEncodedUtils.format(getJobKeyParameter(jobkey), "UTF-8"), null);
    }

}
