package com.indeed.builder;

import com.indeed.domain.ReportAttachment;

import javax.ejb.Stateless;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Stateless
public class ZIPBuilder {

    public void createArchiveWithReports(String outputArchiveName, ReportAttachment ... reports) {
        try {
            FileOutputStream fos = new FileOutputStream(outputArchiveName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (ReportAttachment report : reports) {
                addToZipFile(report.getContent(), report.getFileName(), zos);
            }

            zos.close();
            fos.close();

        } catch (IOException e) {
            Logger.getLogger(ZIPBuilder.class.getName()).log(Level.WARNING, "Error while creating archive", e);
        }
    }

    private void addToZipFile(byte[] content, String fileName, ZipOutputStream zos) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(content);

        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;

        while ((length = bais.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        bais.close();
    }
}


