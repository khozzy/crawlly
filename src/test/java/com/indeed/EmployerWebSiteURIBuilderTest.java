package com.indeed;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class EmployerWebSiteURIBuilderTest {

    private EmployerWebSiteURIBuilder uriBuilder;

    @Before
    public void init() {
        uriBuilder = new EmployerWebSiteURIBuilder();
    }

    @Test
    public void testIfURIIsCorrect() throws URISyntaxException {
        URI uri = uriBuilder.getEmployerURI("93057e67086ea3f5");
        String expected = "http://ie.indeed.com/rc/clk?jk=93057e67086ea3f5";

        Assert.assertEquals(expected, uri.toString());
    }
}
