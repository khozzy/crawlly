package com.common.control;

import com.common.annotation.BufferSize;
import com.common.annotation.LimitHost;
import com.common.entity.ContentInfo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class UrlManager {

    @Inject @BufferSize
    private Integer bufferSize;

    @Inject @LimitHost
    private Boolean limitHost;

    public URL verifyUrl(String url) {
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://"))
            return null;

        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }
        return verifiedUrl;
    }

    public ContentInfo retrieveContentInfo(URL currentUrl) throws Exception {
        HttpURLConnection httpConn = (HttpURLConnection)  currentUrl.openConnection();
        ContentInfo response = new ContentInfo();

        httpConn.setDefaultUseCaches(false);

        response.setResponseCode(httpConn.getResponseCode());
        response.setContentType(httpConn.getContentType());
        response.setLastModified(new Date(httpConn.getLastModified()));

        httpConn.disconnect();

//        strUrl = currentUrl.toString();
//        hashCode = Util.getHashValue(strUrl);
        response.setProtocol(currentUrl.getProtocol());

        return response;
    }

    public  String downloadPage(URL currentUrl) {
        try {
            // Open connection to URL for reading.
            BufferedReader reader = new BufferedReader(new InputStreamReader(currentUrl.openStream()));
            // Read page into buffer.
            String line;
            StringBuffer pageBuffer = new StringBuffer(bufferSize);
            while ((line = reader.readLine()) != null) {
                pageBuffer.append(line);

            }

            return pageBuffer.toString();

        } catch (Exception e) {
            Logger.getLogger(UrlManager.class.getName()).log(Level.INFO, "Error downloading page content (" + currentUrl.getPath() + ")");

        }
        return null;
    }


    public ArrayList<String> retrieveLinks(URL pageUrl, String pageContents, HashSet crawledList) {
        Pattern p = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]",
                    Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(pageContents);

        // Create list of link matches.
        ArrayList linkList = new ArrayList();

        while (m.find()) {
            String link = m.group(1).trim();
            // Skip empty links.
            if (link.length() < 1) {
                continue;
            }
            // Skip links that are just page anchors.
            if (link.charAt(0) == '#') {
                continue;
            }
            // Skip mailto links.
            if (link.indexOf("mailto:") != -1) {
                continue;
            }
            // Skip JavaScript links.
            if (link.toLowerCase().indexOf("javascript") != -1) {
                continue;
            }

            if(link.startsWith("."))
                link = link.substring(1);


            //get baseURL
            String baseUrl = getBaseUrl(pageUrl);

            // System.out.println("baseUrl : " + baseUrl);
            // Prefix absolute and relative URLs if necessary.
            if (link.indexOf("://") == -1) {
                // Handle absolute URLs.
                if (link.charAt(0) == '/') {
                    link = "http://" + pageUrl.getHost() + link;
                    // Handle relative URLs.
                } else {
                    String file = pageUrl.getFile();
                    if (file.indexOf('/') == -1) {
                        link = "http://" + pageUrl.getHost() + "/" + link;
                    } else {
                        link = baseUrl + link;
                    }
                }
            }

            // Remove anchors from link.
            int index = link.indexOf('#');
            if (index != -1) {
                link = link.substring(0, index);
            }
            link = removeTrailingSlashFromUrl(link);
            // Verify link and skip if invalid.
            URL verifiedLink = verifyUrl(link);
            if (verifiedLink == null) {
                continue;
            }
          /* If specified, limit links to those
          having the same host as the start URL. */
            if (limitHost && !pageUrl.getHost().toLowerCase().equals(verifiedLink.getHost().toLowerCase())) {
                continue;
            }
            // Skip link if it has already been crawled.
            if (crawledList.contains(link)) {
                continue;
            }
            // Add link to list.
            linkList.add(link);
        }


        return (linkList);
    }//

    private String getBaseUrl(URL pageUrl) {
        String urlStr   = pageUrl.toString();
        String urlFile  = pageUrl.getFile();
        String protocol = pageUrl.getProtocol();

        protocol = protocol +"://";
        String urlNoProtocol = urlStr.toLowerCase().substring(protocol.length());
        String urlPath;

        if (urlNoProtocol.indexOf("/") == -1 ){
            urlStr = urlStr + "/";
            return urlStr;
        } else {
            urlPath = urlFile.substring(urlFile.lastIndexOf("/"));
        }


        if ( !urlStr.endsWith( "/" ) && urlPath.lastIndexOf(".")==-1 && urlPath.lastIndexOf("?")==-1 )
            return urlStr + "/";

        return urlStr.substring( 0, urlStr.lastIndexOf( '/' ) + 1 );
    }

    private String removeTrailingSlashFromUrl(String tUrl) {
        if(tUrl.endsWith("/"))
        {
            tUrl = tUrl.substring(0,tUrl.length()-1);
        }
        return tUrl;
    }
}
