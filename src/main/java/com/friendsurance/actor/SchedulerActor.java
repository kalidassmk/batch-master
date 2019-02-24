package com.friendsurance.actor;

import com.friendsurance.dto.Schedule;
import com.friendsurance.mail.EmailService;
import com.friendsurance.processing.ItemProcessing;
import com.friendsurance.processing.UserItemProcessor;
import com.friendsurance.reader.ItemReader;
import com.friendsurance.vo.User;
import com.friendsurance.vo.UserStatus;
import com.friendsurance.writer.ItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Scheduler actor.
 *
 *
 * Scheduler Schedule the  Email batch processing And sending emails every night at 3 AM
 */
public class SchedulerActor implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerActor.class);


    private Schedule schedule;

    /**
     * The Item reader.
     */
    ItemReader<User> itemReader;

    /**
     * The Item writer.
     */
    ItemWriter<UserStatus> itemWriter;

    /**
     * The Email service.
     */
    EmailService emailService;

    /**
     * Instantiates a new Scheduler actor.
     *
     * @param schedule     the schedule
     * @param itemReader   the item reader
     * @param itemWriter   the item writer
     * @param emailService the email service
     */
    public SchedulerActor(Schedule schedule, ItemReader itemReader, ItemWriter itemWriter, EmailService emailService) {
        this.schedule = schedule;
        this.itemReader = itemReader;
        this.itemWriter = itemWriter;
        this.emailService = emailService;
    }

    /**
     *  Schedule start the Email Batch processing
     */
    public void run() {
        LOGGER.info("SchedulerActor has started");
        processCommand();
    }

    /**
     *  Schedule process the Email Batch processing
     */
    private void processCommand() {
        try {
            LOGGER.info("Start the email batch processing ");
            ItemProcessing processor = new UserItemProcessor(itemReader, itemWriter, emailService);
            processor.doProcessing();
            LOGGER.info("End the email batch processing ");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Exception while start the email batch processing", e);
        }
    }

    @Override
    public String toString() {
        return this.schedule.toString();
    }
}
