package com.codecool.shop.controller;

import com.codecool.shop.config.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUtility {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationController.class);
    private static final MyConfig cfg = MyConfig.getInstance();

    public static void sendMail(String recipientList, String subject, String mailContent) {

        starterLog(recipientList);

        String senderEmailAddress = cfg.getProperty("sender_email_address");
        String mailPassword = cfg.getProperty("mail_password");

        Properties sessionProperties = generatePropertiesFromConfigFile();

        Session session = CreateSmtpSession(senderEmailAddress, mailPassword, sessionProperties);

        try {

            sendMail(recipientList, subject, mailContent, senderEmailAddress, session);

        } catch (MessagingException e) {
            logger.error("Failure in confirmation e-mail sending. E-mail(s):{}", recipientList, e);
        }
    }

    private static void starterLog(String recipientList) {
        logger.info("Start sending mail for: {}", recipientList);
        if(recipientList.equals("")){
            logger.warn("MailUtility working with an empty recipient list");
        }
    }

    private static void sendMail(String recipientList, String subject, String mailContent, String SENDER_EMAIL_ADDRESS, Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientList)
        );
        message.setSubject(subject);
        message.setContent(mailContent, "text/html");
        Transport.send(message);
        logger.info("Confirmation e-mail was sent to: {}", recipientList);
    }

    private static Session CreateSmtpSession(String SENDER_EMAIL_ADDRESS, String PASSWORD, Properties properties) {
        return Session.getInstance(properties,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, PASSWORD);
                        }
                    });
    }

    private static Properties generatePropertiesFromConfigFile() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", cfg.getProperty("mail_smtp_host"));
        prop.put("mail.smtp.port", cfg.getProperty("mail_smtp_port"));
        prop.put("mail.smtp.auth", cfg.getProperty("mail_smtp_auth"));
        prop.put("mail.smtp.socketFactory.port", cfg.getProperty("mail_smtp_socketFactory_port"));
        prop.put("mail.smtp.socketFactory.class", cfg.getProperty("mail_smtp_socketFactory_class"));
        return prop;
    }
}





