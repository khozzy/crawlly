package com.indeed.control;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class Mail {

    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    public void send() {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ali@baba.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("khozzy@gmail.com"));
            message.setSubject("testowy temat");
            message.setText("Simple text");

            Transport.send(message);

        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }
}
