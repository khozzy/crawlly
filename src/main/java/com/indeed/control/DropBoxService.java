package com.indeed.control;

import com.dropbox.core.*;
import com.indeed.builder.ZIPBuilder;
import com.indeed.domain.ReportAttachment;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Path("/dropbox")
public class DropBoxService {

    private DbxAppInfo appInfo;
    private DbxRequestConfig config;
    private DbxWebAuthNoRedirect webAuth;
    private DbxClient client;

    private final String APP_KEY = "bfcj5dgatfrf87e";
    private final String APP_SECRET = "5bcud2ug0j12xlg";
    private final String ACCESS_TOKEN = "vlvFMoJrfnAAAAAAAAAAAWhzynEgpwyHFH2Vvr3DW9H-xOqOhfIQ1M28XHLaFAfz";

    @Inject
    private ZIPBuilder zipBuilder;

    @PostConstruct
    public void init() {
        System.out.println("Init");
        appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
        webAuth = new DbxWebAuthNoRedirect(config, appInfo);
    }

    @GET @Path("/auth")
    @Produces(MediaType.TEXT_PLAIN)
    public String startAuth() {
        return webAuth.start();
    }

    @GET @Path("/finish/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    public String finish(@PathParam("code") String code) throws DbxException {
        DbxAuthFinish authFinish = webAuth.finish(code);
        return authFinish.accessToken;
    }

    public void uploadReports(ReportAttachment ... reports) throws IOException {
        client = new DbxClient(config, ACCESS_TOKEN);

        String reportsZipFileDirectory = "/tmp/";
        String reportsZipFileName = getDateFormatted() + "_reports.zip";
        String reportsZipLocation = reportsZipFileDirectory + reportsZipFileName;

        zipBuilder.createArchiveWithReports(reportsZipLocation, reports);

        File inputFile = new File(reportsZipLocation);
        FileInputStream inputStream = new FileInputStream(inputFile);

        DbxEntry.File uploadedFile = null;

        try {
            uploadedFile = client.uploadFile("/" + reportsZipFileName, DbxWriteMode.add(), inputFile.length(), inputStream);
            removeFileFromTmp(reportsZipLocation);

            System.out.println("Uploaded to DropBox: " + uploadedFile.toString());
        } catch (DbxException e) {
            Logger.getLogger(DropBoxService.class.getName()).log(Level.WARNING, "Upload to DropBox failed, file is stored in " + reportsZipLocation, e);
        }

        inputStream.close();
    }

    private void removeFileFromTmp(String location) {
        File file = new File(location);

        if (file.delete()) {
            Logger.getLogger(DropBoxService.class.getName()).log(Level.INFO, "Report file was deleted");
        } else {
            Logger.getLogger(DropBoxService.class.getName()).log(Level.WARNING, "Cannot delete file " + location);
        }
    }

    private String getDateFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(new Date());
    }
}
