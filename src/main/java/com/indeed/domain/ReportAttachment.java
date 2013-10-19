package com.indeed.domain;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

public class ReportAttachment {

    private String contentType;
    private byte[] content;
    private String fileName;

    public ReportAttachment(String fileName, String contentType, byte[] content) {
        this.contentType = contentType;
        this.content = content;
        this.fileName = fileName;
    }

    public MimeBodyPart getAttachmentMimeBodyPart() throws MessagingException {
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.setDataHandler(new DataHandler(new ByteArrayDataSource(content, contentType)));
        attachment.setFileName(fileName);

        return attachment;
    }
}
