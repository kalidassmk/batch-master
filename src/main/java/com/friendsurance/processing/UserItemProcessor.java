package com.friendsurance.processing;


import com.friendsurance.exception.AppException;
import com.friendsurance.mail.EmailService;
import com.friendsurance.reader.ItemReader;
import com.friendsurance.vo.User;
import com.friendsurance.vo.UserStatus;
import com.friendsurance.writer.ItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * UserItemProcessor help to process the user data as the business condition
 *
 * @author Kalidass Mahalingam 2/2/2019
 */
public class UserItemProcessor extends ItemProcessing<User, UserStatus> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserItemProcessor.class);


    /**
     * Instantiates a new User item processor.
     *
     * @param reader       the reader
     * @param writer       the writer
     * @param emailService the email service
     */
    public UserItemProcessor(ItemReader<User> reader, ItemWriter<UserStatus> writer, EmailService emailService) {
        super(reader, writer, emailService);
    }

    /**
     * The Mail type 1.
     */
    Predicate<User> MAIL_TYPE_1 = e -> !e.isHasContract() && e.getFriendsNumber() > 3 && e.getSentInvitationsNumber() > 1;
    /**
     * The Mail type 2.
     */
    Predicate<User> MAIL_TYPE_2 = e -> !e.isHasContract() && e.getFriendsNumber() == 0 && e.getSentInvitationsNumber() == 0 ||
            !e.isHasContract() && e.getFriendsNumber() < 3 && e.getSentInvitationsNumber() > 1;
    /**
     * The Mail type 3.
     */
    Predicate<User> MAIL_TYPE_3 = e -> (!e.isHasContract() && (e.getFriendsNumber() == 2) && (e.getSentInvitationsNumber() == 0 || e.getSentInvitationsNumber() > 6)) ||
            (e.isHasContract() && e.getFriendsNumber() == 0 && (e.getSentInvitationsNumber() == 0 || e.getSentInvitationsNumber() > 3));
    /**
     * The Mail type 4.
     */
    Predicate<User> MAIL_TYPE_4 = e -> e.isHasContract() && (e.getFriendsNumber() > 1 && e.getFriendsNumber() < 5) && (e.getSentInvitationsNumber() > 2 && e.getSentInvitationsNumber() < 4);
    /**
     * The Mail type 5.
     */
    Predicate<User> MAIL_TYPE_5 = e -> e.isHasContract() && e.getFriendsNumber() > 4 && e.getSentInvitationsNumber() > 3;

    /**
     * Send mail completion stage.
     *
     * @param item the item
     * @return the completion stage
     * @throws AppException the app exception
     */
    @Override
    protected CompletionStage<List<UserStatus>> sendMail(List<UserStatus> statusList) {
        return CompletableFuture.supplyAsync(() -> {
            statusList.forEach(userStatus ->
                    {
                        try {
                            emailService.sendMail(userStatus.getEmail(), EmailService.MailType.valueOf(userStatus.getMailType())).thenApply(a -> {
                                if (a) {
                                    userStatus.setStatus("SUCCESS");
                                } else {
                                    userStatus.setStatus("FAILED");

                                }
                                return a;
                            }).toCompletableFuture().get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return statusList;
        });
    }


    /**
     * This method is responsible for processing of each individual item. Subclasses should define
     * custom processing logic here.
     *
     * @param user Item to process
     * @return result which will be passed to ItemWriter. Can be null if no processing was possible.
     * @throws AppException the app exception
     */
    @Override
    public CompletionStage<List<UserStatus>> process(List<User> user) throws AppException {

        return CompletableFuture.supplyAsync(() -> {

            List<UserStatus> mailTypeFiveUserList;
            try {
                LOGGER.info("Processing logic  started ...................................................");
                mailTypeFiveUserList = user.parallelStream().filter(MAIL_TYPE_5).map(a -> {
                    UserStatus status = new UserStatus() {{
                        setEmail(a.getEmail());
                        setMailType(EmailService.MailType.MAIL_TYPE_5.name());
                    }};
                    return status;
                }).collect(Collectors.toList());

                List<String> mailTypeFiveEmailList = mailTypeFiveUserList.parallelStream().map(a -> a.getEmail()).collect(Collectors.toList());
                LOGGER.info("Mail Type Five User Count : " + mailTypeFiveEmailList.size());


                List<UserStatus> mailTypeFourUserList = user.parallelStream().filter(MAIL_TYPE_4).filter(a -> !mailTypeFiveEmailList.contains(a.getEmail())).map(a -> {
                    UserStatus status = new UserStatus() {{
                        setEmail(a.getEmail());
                        setMailType(EmailService.MailType.MAIL_TYPE_4.name());
                    }};
                    return status;
                }).collect(Collectors.toList());

                List<String> mailTypeFourEmailList = mailTypeFourUserList.parallelStream().map(a -> a.getEmail()).collect(Collectors.toList());
                LOGGER.info("Mail Type Four User Count : " + mailTypeFourEmailList.size());


                List<UserStatus> mailTypeThreeUserList = user.parallelStream().filter(MAIL_TYPE_3).filter(a -> !mailTypeFiveEmailList.contains(a.getEmail())
                        && !mailTypeFourEmailList.contains(a.getEmail())).map(a -> {
                    UserStatus status = new UserStatus() {{
                        setEmail(a.getEmail());
                        setMailType(EmailService.MailType.MAIL_TYPE_3.name());
                    }};
                    return status;
                }).collect(Collectors.toList());

                List<String> mailTypeThreeEmailList = mailTypeThreeUserList.parallelStream().map(a -> a.getEmail()).collect(Collectors.toList());
                LOGGER.info("Mail Type Three User Count : " + mailTypeThreeEmailList.size());


                List<UserStatus> mailTypeTwoUserList = user.parallelStream().filter(MAIL_TYPE_2).filter(a -> !mailTypeFiveEmailList.contains(a.getEmail())
                        && !mailTypeFourEmailList.contains(a.getEmail()) && !mailTypeThreeEmailList.contains(a.getEmail())).map(a -> {
                    UserStatus status = new UserStatus() {{
                        setEmail(a.getEmail());
                        setMailType(EmailService.MailType.MAIL_TYPE_2.name());
                    }};
                    return status;
                }).collect(Collectors.toList());

                List<String> mailTypeTwoEmailList = mailTypeTwoUserList.parallelStream().map(a -> a.getEmail()).collect(Collectors.toList());
                LOGGER.info("Mail Type Two User Count : " + mailTypeTwoEmailList.size());


                List<UserStatus> mailTypeOneUserList = user.parallelStream().filter(MAIL_TYPE_1).filter(a -> !mailTypeFiveEmailList.contains(a.getEmail())
                        && !mailTypeFourEmailList.contains(a.getEmail()) && !mailTypeThreeEmailList.contains(a.getEmail()) && !mailTypeTwoEmailList.contains(a.getEmail())).map(a -> {
                    UserStatus status = new UserStatus() {{
                        setEmail(a.getEmail());
                        setMailType(EmailService.MailType.MAIL_TYPE_1.name());
                    }};
                    return status;
                }).collect(Collectors.toList());

                LOGGER.info("Mail Type One User Count : " + mailTypeOneUserList.size());


                mailTypeFiveUserList.addAll(mailTypeFourUserList);
                mailTypeFiveUserList.addAll(mailTypeThreeUserList);
                mailTypeFiveUserList.addAll(mailTypeTwoUserList);
                mailTypeFiveUserList.addAll(mailTypeOneUserList);

                LOGGER.info("Total user has processed : " + mailTypeFiveUserList.size());

                return mailTypeFiveUserList;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("");
            }
        });

    }

}