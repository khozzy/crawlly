package com.indeed.control;

import com.dropbox.core.*;
import com.indeed.builder.ZIPBuilder;
import com.indeed.domain.ReportAttachment;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

@Singleton
public class DropboxService {

    private DbxAppInfo appInfo;
    private DbxRequestConfig config;
    private DbxWebAuthNoRedirect webAuth;
    private DbxClient client;

    // Roberts
//    private final String APP_KEY = "7i61kz14kdebkq6";
//    private final String APP_SECRET = "nhe0hc77k1scxly";
//    private final String ACCESS_TOKEN = "24IEiuSY1RIAAAAAAAAAAXXw6n6gIbYMVKHLj-KXZaI";

    // Norberts
    private final String APP_KEY = "bfcj5dgatfrf87e";
    private final String APP_SECRET = "5bcud2ug0j12xlg";
    private final String ACCESS_TOKEN = "Hbb6NeS6qgYAAAAAAAAAASDYKXocdRKUi9NClykN7dC4SKePamm7YWyHvtsl9Kua";

    @Inject
    private ZIPBuilder zipBuilder;

    @PostConstruct
    public void init() {
        appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
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

    public void uploadReports(ReportAttachment ... reports) throws IOException, DbxException {
        client = new DbxClient(config, ACCESS_TOKEN);

        String reportsZipFileDirectory = "/tmp/";
        String reportsZipFileName = "reports.zip";
        String reportsZipLocation = reportsZipFileDirectory + reportsZipFileName;

        zipBuilder.createArchiveWithReports(reportsZipLocation, reports);

        File inputFile = new File(reportsZipLocation);
        FileInputStream inputStream = new FileInputStream(inputFile);

        DbxEntry.File uploadedFile = client.uploadFile("/" + reportsZipFileName, DbxWriteMode.add(), inputFile.length(), inputStream);

        System.out.println("Uploaded: " + uploadedFile.toString());

        inputStream.close();
    }
}
