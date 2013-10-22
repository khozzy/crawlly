package com.indeed.control;


import com.dropbox.core.DbxException;
import org.junit.Test;

import java.io.IOException;

public class DropBoxTest {

    private Dropbox dropbox;

    @Test
    public void authorizeTest() {
        dropbox = new Dropbox("bfcj5dgatfrf87e", "5bcud2ug0j12xlg");
        try {
            dropbox.uploadFile("Hbb6NeS6qgYAAAAAAAAAASDYKXocdRKUi9NClykN7dC4SKePamm7YWyHvtsl9Kua");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (DbxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
