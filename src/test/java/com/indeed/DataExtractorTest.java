package com.indeed;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class DataExtractorTest {

    private DataExtractor dataExtractor;

    @Before
    public void init() {
        dataExtractor = new DataExtractor();
    }

    @Test
    public void testFirstEmployerPage() throws IOException {
        dataExtractor.setSource(getClass().getResourceAsStream("/com/indeed/employerSite/employer_site1.html"));

        Set<String> emails = dataExtractor.getEmails();
        Set<String> phones = dataExtractor.getPhoneNumbers();

        for (String phone : phones) {
            System.out.println("phone = " + phone);
        }

        Assert.assertNotNull(phones);
        Assert.assertNotNull(emails);

        Assert.assertEquals(1,phones.size());
        Assert.assertEquals(1,emails.size());

        Assert.assertEquals(true, phones.contains("+353 1 869 0219"));
        Assert.assertEquals(true, emails.contains("Stephanie.Gavin@italliancegroup.com"));


    }

    @Test
    public void testSecondEmployerPage() throws IOException {
        dataExtractor.setSource(getClass().getResourceAsStream("/com/indeed/employerSite/employer_site2.html"));

        Set<String> emails = dataExtractor.getEmails();
        Set<String> phones = dataExtractor.getPhoneNumbers();

        Assert.assertNotNull(phones);
        Assert.assertNotNull(emails);

        Assert.assertEquals(2,phones.size());
        Assert.assertEquals(2,emails.size());

        Assert.assertEquals(true, phones.contains("+44 207 443 5400"));
        Assert.assertEquals(true, phones.contains("+353 1 4853452"));

        Assert.assertEquals(true, emails.contains("hr@mttnow.com"));
        Assert.assertEquals(true, emails.contains("info@mttnow.com"));
    }

    @Test
    public void testThirdEmployerPage() throws IOException {
        dataExtractor.setSource(getClass().getResourceAsStream("/com/indeed/employerSite/employer_site3.html"));

        Set<String> emails = dataExtractor.getEmails();
        Set<String> phones = dataExtractor.getPhoneNumbers();

        Assert.assertNotNull(phones);
        Assert.assertNotNull(emails);

        Assert.assertEquals(1,phones.size());
        Assert.assertEquals(1,emails.size());

        Assert.assertEquals(true, phones.contains("+44 (0)203 510 2115"));
        Assert.assertEquals(true, emails.contains("jobs@groupon.co.uk"));
    }

//    @Test
//    public void testFourthEmployerPage() throws IOException {
//        dataExtractor.setSource(getClass().getResourceAsStream("/com/indeed/employerSite/employer_site4.html"));
//        Set<String> emails = dataExtractor.getEmails();
//
//        for (String email : emails) {
//            System.out.println(email);
//        }
//
//        Set<String> phones = dataExtractor.getPhoneNumbers();
//        // tu ma byc jeden telefon
//        for (String phone : phones) {
//            System.out.println("phone = " + phone);
//        }
//
//        Assert.assertTrue(true);
//    }
}
