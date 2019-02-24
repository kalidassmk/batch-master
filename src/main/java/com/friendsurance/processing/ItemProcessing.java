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
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

/**
 * Main processing class which should be used and extended.
 * Data to be precessed will be retrieved from ItemReader.
 * After data has been processed the result will be passed to ItemWriter.
 *
 * @param <I> the type parameter
 * @param <O> the type parameter
 */
public abstract class ItemProcessing<I, O> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemProcessing.class);

    private ItemReader<I> reader;

    private ItemWriter<O> writer;

    /**
     * The Email service.
     */
    EmailService emailService;


    /**
     * Instantiates a new Item processing.
     *
     * @param reader       the reader
     * @param writer       the writer
     * @param emailService the email service
     */
    protected ItemProcessing(ItemReader<I> reader, ItemWriter<O> writer, EmailService emailService) {
        this.reader = reader;
        this.writer = writer;
        this.emailService = emailService;
    }

    /**
     * This method is responsible for processing of each individual item. Subclasses should define
     * custom processing logic here.
     *
     * @param item Item to process
     * @return result which will be passed to ItemWriter. Can be null if no processing was possible.
     * @throws AppException the app exception
     */
    protected abstract CompletionStage<List<UserStatus>> process(List<User> item) throws AppException;

    /**
     * Send mail completion stage.
     *
     * @param item the item
     * @return the completion stage
     * @throws AppException the app exception
     */
    protected abstract CompletionStage<List<UserStatus>> sendMail(List<UserStatus> item) throws AppException;


    /**
     * Main processing method which will be called by client code.
     * <p/>
     * It will receive items from ItemReader, apply processing to each
     * individual item and forward results to ItemWriter.
     *
     * @throws AppException the app exception
     */
    public final void doProcessing() throws AppException {
        try {
            LOGGER.info(" Get the User details to start the process...............");

            reader.read().thenCompose(user -> {
                try {
                    LOGGER.info("Process the user details ...............");
                    return process(user).thenCompose(userStatusList -> {
                        try {
                            LOGGER.info("Send the mail ...............");
                            return sendMail(userStatusList).thenCompose(finalUserList -> {
                               try {
                                   LOGGER.info("Write the user status ...............");
                                   return writer.write(userStatusList).exceptionally(e -> {
                                       LOGGER.error("Update UserStatus into DB has Completed exceptionally....",e);
                                        return Boolean.FALSE;
                                   });
                               } catch (AppException e) {
                                   e.printStackTrace();
                                   LOGGER.error("Exception while write the user data....",e);
                               }
                               return null;
                           });
                        } catch (AppException e) {
                            e.printStackTrace();
                            LOGGER.error("Exception while send the user mail ....",e);
                        }
                        return null;
                    });
                } catch (AppException e) {
                    e.printStackTrace();
                    LOGGER.error("Exception while process the user data....",e);
                }
                return null;
            }).exceptionally(e -> {
                LOGGER.error("Completed exceptionally....",e);
                return null;
            }).toCompletableFuture().get();

        } catch (AppException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            LOGGER.error("Exception while read the user data....",e);
            throw new AppException("Exception while do the processing.............." + e.getMessage());
        }

    }

}
