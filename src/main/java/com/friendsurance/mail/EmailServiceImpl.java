package com.friendsurance.mail;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * This class help to send the user email
 *
 * @author Kalidass Mahalingam 3/2/2019
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public CompletionStage<Boolean> sendMail(String recipient, MailType mailType) {
        return CompletableFuture.supplyAsync(() -> {
            final String smtpHostServer = "smtp.gmail.com";
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpHostServer);
            Session session = Session.getInstance(props, null);
            send(session, recipient, getSubject(mailType), getBody(mailType));
            return Boolean.TRUE;
        });

    }


    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    private static Boolean send(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("kalidass.mks@gmail.com.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("kalidass.mks@gmail.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            //  Transport.send(msg);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getSubject(MailType mailType) {
        switch (mailType.name()) {
            case "MAIL_TYPE_1":
                return "MAIL_TYPE_1";
            case "MAIL_TYPE_2":
                return "MAIL_TYPE_2";
            case "MAIL_TYPE_3":
                return "MAIL_TYPE_3";
            case "MAIL_TYPE_4":
                return "MAIL_TYPE_4";
            case "MAIL_TYPE_5":
                return "MAIL_TYPE_5";
            default:
                return "";
        }
    }

    /**
     * Gets body.
     *
     * @param mailType the mail type
     * @return the body
     */
    public String getBody(MailType mailType) {
        switch (mailType.name()) {
            case "MAIL_TYPE_1":
                return "MAIL_TYPE_1";
            case "MAIL_TYPE_2":
                return "MAIL_TYPE_2";
            case "MAIL_TYPE_3":
                return "MAIL_TYPE_3";
            case "MAIL_TYPE_4":
                return "MAIL_TYPE_4";
            case "MAIL_TYPE_5":
                return "MAIL_TYPE_5";
            default:
                return "";
        }
    }


}

