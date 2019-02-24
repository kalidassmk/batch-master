package com.friendsurance.actor;

import com.friendsurance.dto.Schedule;
import com.friendsurance.mail.EmailService;
import com.friendsurance.reader.ItemReader;
import com.friendsurance.vo.User;
import com.friendsurance.vo.UserStatus;
import com.friendsurance.writer.ItemWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

/**
 * com.friendsurance.actor
 *
 * @author Kalidass Mahalingam 5/2/2019
 */
public class SchedulerActorTest {
    /**
     * The Logger.
     */
    @Mock
    Logger LOGGER;
    /**
     * The Schedule.
     */
    @Mock
    Schedule schedule;
    /**
     * The Item reader.
     */
    @Mock
    ItemReader<User> itemReader;
    /**
     * The Item writer.
     */
    @Mock
    ItemWriter<UserStatus> itemWriter;
    /**
     * The Email service.
     */
    @Mock
    EmailService emailService;
    /**
     * The Scheduler actor.
     */
    @InjectMocks
    SchedulerActor schedulerActor;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test run.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRun() throws Exception {
        schedulerActor.run();
    }

    /**
     * Test to string.
     *
     * @throws Exception the exception
     */
    @Test
    public void testToString() throws Exception {
        String result = schedulerActor.toString();
        Assert.assertEquals("schedule", result);
    }
}

