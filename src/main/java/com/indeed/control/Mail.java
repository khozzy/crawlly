package com.indeed.control;

import com.indeed.domain.ReportAttachment;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class Mail {

    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    public void send(String addresses, String topic, String textMessage) {

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);

            Transport.send(message);

        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }

    public void send(String addresses, String topic, String textMessage, ReportAttachment ... attachments) throws IOException {

        try {

            Message message = new MimeMessage(session);

            // Headers
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);

            // Body
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(textMessage);

            // Combine all together
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            for (ReportAttachment attachment : attachments) {
                multipart.addBodyPart(attachment.getAttachmentMimeBodyPart());
            }

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }
}
