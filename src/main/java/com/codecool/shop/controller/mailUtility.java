package com.codecool.shop.controller;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class mailUtility {
    /*public static void sendTestMail(){
        final String username = "mindentnagyonjolprogramozomiki@gmail.com";
        final String password = "u2mtkBRf544PLioUy";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mindentnagyonjolprogramozomiki@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("mindentnagyonjolprogramozomiki@gmail.com")
            );
            message.setSubject("best plants test");
            message.setText("best plants,"
                    + "\n\n valami nagyon okos teszt üzenet");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/

    public static void sendMail(String recipientList, String subject, String mailContent){
        final String username = "mindentnagyonjolprogramozomiki@gmail.com";
        final String password = "u2mtkBRf544PLioUy";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mindentnagyonjolprogramozomiki@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientList)
            );
            message.setSubject(subject);
            message.setText(mailContent);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        }
    }





