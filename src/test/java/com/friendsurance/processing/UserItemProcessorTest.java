package com.friendsurance.processing;

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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Predicate;

import static org.mockito.Mockito.*;

/**
 * com.friendsurance.processing
 *
 * @author Kalidass Mahalingam 5/2/2019
 */
public class UserItemProcessorTest {
    /**
     * The Logger.
     */
    @Mock
    Logger LOGGER;
    /**
     * The Mail type 1.
     */
    @Mock
    Predicate<User> MAIL_TYPE_1;
    /**
     * The Mail type 2.
     */
    @Mock
    Predicate<User> MAIL_TYPE_2;
    /**
     * The Mail type 3.
     */
    @Mock
    Predicate<User> MAIL_TYPE_3;
    /**
     * The Mail type 4.
     */
    @Mock
    Predicate<User> MAIL_TYPE_4;
    /**
     * The Mail type 5.
     */
    @Mock
    Predicate<User> MAIL_TYPE_5;
    /**
     * The Reader.
     */
    @Mock
    ItemReader reader;
    /**
     * The Writer.
     */
    @Mock
    ItemWriter writer;
    /**
     * The Email service.
     */
    @Mock
    EmailService emailService;
    /**
     * The User item processor.
     */
    @InjectMocks
    UserItemProcessor userItemProcessor;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    /**
     * Do process.
     *
     * @throws Exception the exception
     */
    @Test
    public void doProcess() throws Exception {
        ItemProcessing item= new UserItemProcessor(reader,writer,emailService);
        userItemProcessor.sendMail(Arrays.<UserStatus>asList(new UserStatus(){{setMailType(EmailService.MailType.MAIL_TYPE_1.name()); setEmail("test@gmail.com");}}));
    }


    /**
     * Test send mail.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSendMail() throws Exception {
        userItemProcessor.sendMail(Arrays.<UserStatus>asList(new UserStatus(){{setMailType(EmailService.MailType.MAIL_TYPE_1.name()); setEmail("test@gmail.com");setTimeStamp(String.valueOf(System.currentTimeMillis()));}}));
    }

    /**
     * Test process.
     *
     * @throws Exception the exception
     */
    @Test
    public void testProcess() throws Exception {
        List<UserStatus> result = userItemProcessor.
                process(Arrays.<User>asList(new User(){{setSentInvitationsNumber(0);setFriendsNumber(0);
                setHasContract(false);setEmail("test@gmail.com");}})).toCompletableFuture().get();
        Assert.assertEquals("test@gmail.com", result.get(0).getEmail());
        Assert.assertEquals(true, result.get(0).getMailType() != null);
        Assert.assertEquals(true, result.get(0).getTimeStamp() == null);

    }

    /**
     * Test do processing.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDoProcessing() throws Exception {
        CompletionStage<List<User>>  future = CompletableFuture.supplyAsync(() -> Arrays.<User>asList(new User(){{setSentInvitationsNumber(0);setFriendsNumber(0);
            setHasContract(false);setEmail("test@gmail.com");}}));
        when(reader.read()).thenReturn(future);
        userItemProcessor.doProcessing();
    }
}

