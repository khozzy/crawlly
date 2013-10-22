package com.indeed.builder;


import org.junit.Test;

public class ZipBuilderTest {

    private ZIPBuilder zipBuilder = new ZIPBuilder();

    @Test
    public void doSmth() {
        zipBuilder.createArchive("/tmp/out.zip");
    }
}
