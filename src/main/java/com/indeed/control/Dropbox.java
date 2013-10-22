package com.indeed.control;

import com.dropbox.core.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class Dropbox {

    private DbxAppInfo appInfo;
    private DbxRequestConfig config;
    private DbxWebAuthNoRedirect webAuth;
    private DbxClient client;

    private final String ACCESS_TOKEN = "Hbb6NeS6qgYAAAAAAAAAASDYKXocdRKUi9NClykN7dC4SKePamm7YWyHvtsl9Kua";

    public Dropbox(String appkey, String appSecret) {
        appInfo = new DbxAppInfo(appkey, appSecret);
        config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
        webAuth = new DbxWebAuthNoRedirect(config, appInfo);
    }

    public String getAccessToken(String code) {
        String authorizeUrl = webAuth.start();
        System.out.println("auth url: " + authorizeUrl);

        DbxAuthFinish authFinish = null;

        try {
            authFinish = webAuth.finish(code);
        } catch (DbxException e) {
            e.printStackTrace();
        }

        return authFinish.accessToken;
    }

    public void uploadFile(String accessToken) throws IOException, DbxException {
        client = new DbxClient(config, accessToken);

        File inputFile = new File("/tmp/file.txt");
        FileInputStream inputStream = new FileInputStream(inputFile);

        DbxEntry.File uploadedFile = client.uploadFile("/example.txt", DbxWriteMode.add(), inputFile.length(), inputStream);
        System.out.println("Uploaded: " + uploadedFile.toString());

        inputStream.close();
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.println("Destroying");
        super.finalize();
    }
}
