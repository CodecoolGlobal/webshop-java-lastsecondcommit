package com.codecool.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class mailUtility {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationController.class);

    public static void sendMail(String recipientList, String subject, String mailContent) {
        logger.info("Start sending mail for: {}", recipientList);
        if(recipientList.equals("")){
            logger.warn("mailUtility working with an empty recipientlist");
        }
        final String SENDER_EMAIL_ADDRESS = "mindentnagyonjolprogramozomiki@gmail.com";
        final String PASSWORD = "u2mtkBRf544PLioUy";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, PASSWORD);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientList)
            );
            message.setSubject(subject);
            //message.setText(mailContent);
            message.setContent(mailContent, "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}





