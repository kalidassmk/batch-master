package com.friendsurance.reader.impl;

import com.friendsurance.vo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * com.friendsurance.reader.impl
 *
 * @author Kalidass Mahalingam 5/2/2019
 */
public class UserItemReaderTest {
    /**
     * The Logger.
     */
    @Mock
    Logger LOGGER;
    /**
     * The Jdbc template object.
     */
    @Mock
    JdbcTemplate jdbcTemplateObject;
    /**
     * The User item reader.
     */
    @InjectMocks
    UserItemReader userItemReader;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test read.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRead() throws Exception {
        List<User> result = userItemReader.read().toCompletableFuture().get();
        Assert.assertEquals(true, result != null);
    }
}

