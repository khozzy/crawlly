package com.indeed.builder;

import javax.ejb.Stateless;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Stateless
public class ZIPBuilder {

    public void createArchive(String outputArchiveName) {
        try {
            FileOutputStream fos = new FileOutputStream(outputArchiveName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            String file1 = "/tmp/file1.txt";
            String file2 = "/tmp/file2.txt";

            addToZipFile(file1, "file1.txt", zos);
            addToZipFile(file2, "file2.txt", zos);

            zos.close();
            fos.close();

        } catch (IOException e) {
            Logger.getLogger(ZIPBuilder.class.getName()).log(Level.WARNING, "Error when creating archive", e);
        }
    }

    private void addToZipFile(String fileLocation, String fileName, ZipOutputStream zos) throws IOException {
        System.out.println("Writing '" + fileLocation + "' to zip file");

        File file = new File(fileLocation);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }
}


