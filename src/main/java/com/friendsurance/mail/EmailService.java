package com.friendsurance.mail;

import com.friendsurance.vo.UserStatus;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * Interface for emails service.
 */
public interface EmailService {

    /**
     * Will send an email to a recipient.
     *
     * @param recipient email recipient
     * @param mailType  type of mail
     * @return the completion stage
     */
    CompletionStage<Boolean> sendMail(String recipient, MailType mailType);

    /**
     * MailType defines which kind of email will be sent
     */
    enum MailType {
        /**
         * Mail type 1 mail type.
         */
        MAIL_TYPE_1,
        /**
         * Mail type 2 mail type.
         */
        MAIL_TYPE_2,
        /**
         * Mail type 3 mail type.
         */
        MAIL_TYPE_3,
        /**
         * Mail type 4 mail type.
         */
        MAIL_TYPE_4,
        /**
         * Mail type 5 mail type.
         */
        MAIL_TYPE_5
    }

}
