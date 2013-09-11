package com.common.entity;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.*;

@RunWith(Arquillian.class)
public class SeedPagePersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Page.class)
                .addClass(SeedPage.class)
                .addClass(SubPage.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction tx;

    private static final String[] URLS = {
            "http://www.google.com",
            "http://www.amazon.com"
    };

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }

    @Test
    public void shouldFindAllSeedPagesUsingJPQLQuery() throws Exception {

        System.out.println("Selecting (using JPQL) ...");
        List<SeedPage> seedPages = em.createQuery("SELECT sp FROM SeedPage sp ORDER BY sp.id").getResultList();

        System.out.println("Found " + seedPages.size() + " seed pages.");
        assertContainsAllSeedPages(seedPages);
    }

    @After
    public void commitTransaction() throws Exception {
        tx.commit();
    }

    private void clearData() throws Exception {
        tx.begin();
        em.joinTransaction();

        System.out.println("Dumping old SeedPages...");
        em.createQuery("DELETE FROM SeedPage").executeUpdate();

        tx.commit();
    }

    private void insertData() throws Exception {
        tx.begin();
        em.joinTransaction();

        System.out.println("Inserting new records...");

        for (String url : URLS) {
            SeedPage seedPage = new SeedPage();
            seedPage.setUrl(url);

            em.persist(seedPage);
        }

        tx.commit();

        em.clear(); // clear the persistence context (first level cache)
    }

    private void startTransaction() throws Exception{
        tx.begin();
        em.joinTransaction();
    }

    private static void assertContainsAllSeedPages(Collection<SeedPage> retrievedSeedPages) {

        Assert.assertEquals(URLS.length, retrievedSeedPages.size());

        final Set<String> retrievedUrls = new HashSet<String>();

        for (SeedPage seedPage : retrievedSeedPages) {
            System.out.println("* " + seedPage.getUrl());
            retrievedUrls.add(seedPage.getUrl());
        }

        Assert.assertTrue(retrievedUrls.containsAll(Arrays.asList(URLS)));
    }

}
