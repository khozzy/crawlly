package com.indeed;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class SearchURIBuilderTest {

    private SearchURIBuilder searchURIBuilder;

    @Before
    public void init() {
        searchURIBuilder = new SearchURIBuilder();
    }

    @Test
    public void testIfBuilderCreated() {
        Assert.assertNotNull(searchURIBuilder);
    }

    @Test
    public void testIfURLForPaginationIsBuildCorrectly() throws URISyntaxException {
        URI uri = searchURIBuilder.getURIForPagination("Java, .NET, ASP, Software Developer, Python, Ruby, Oracle", "Ireland", 0, 10);
        String expected = "http://api.indeed.com/ads/apisearch?publisher=5004251131744383&v=2&format=json&sort=relevance&radius=25&st=employer&jt=&highlight=0&filter=1&latlong=1&co=ie&chnl=&userip=1.2.3.4&useragent=Java%2F1.7.0_25&start=0&limit=10&q=Java%2C+.NET%2C+ASP%2C+Software+Developer%2C+Python%2C+Ruby%2C+Oracle&l=Ireland";

        Assert.assertEquals(expected, uri.toString());
    }

    @Test
    public void testIfURLForFromageIsBuildCorrectly() throws URISyntaxException {
        URI uri = searchURIBuilder.getURIForFromage("Java, .NET, ASP, Software Developer, Python, Ruby, Oracle", "Ireland",10, 10, 10);
        String expected = "http://api.indeed.com/ads/apisearch?publisher=5004251131744383&v=2&format=json&sort=relevance&radius=25&st=employer&jt=&highlight=0&filter=1&latlong=1&co=ie&chnl=&userip=1.2.3.4&useragent=Java%2F1.7.0_25&start=10&limit=10&fromage=10&q=Java%2C+.NET%2C+ASP%2C+Software+Developer%2C+Python%2C+Ruby%2C+Oracle&l=Ireland";

        Assert.assertEquals(expected, uri.toString());
    }
}
