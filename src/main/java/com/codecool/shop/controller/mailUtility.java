package com.codecool.shop.controller;

import com.codecool.shop.config.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class mailUtility {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationController.class);
    private static final MyConfig cfg = MyConfig.getInstance();

    public static void sendMail(String recipientList, String subject, String mailContent) {
        logger.info("Start sending mail for: {}", recipientList);
        if(recipientList.equals("")){
            logger.warn("mailUtility working with an empty recipient list");
        }

        final String SENDER_EMAIL_ADDRESS = cfg.getProperty("sender_email_address");;
        final String PASSWORD = cfg.getProperty("mail_password");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", cfg.getProperty("mail_smtp_host"));
        prop.put("mail.smtp.port", cfg.getProperty("mail_smtp_port"));
        prop.put("mail.smtp.auth", cfg.getProperty("mail_smtp_auth"));
        prop.put("mail.smtp.socketFactory.port", cfg.getProperty("mail_smtp_socketFactory_port"));
        prop.put("mail.smtp.socketFactory.class", cfg.getProperty("mail_smtp_socketFactory_class"));

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
            message.setContent(mailContent, "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}





