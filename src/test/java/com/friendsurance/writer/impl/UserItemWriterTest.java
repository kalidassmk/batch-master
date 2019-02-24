package com.friendsurance.writer.impl;

import com.friendsurance.vo.UserStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * com.friendsurance.writer.impl
 *
 * @author Kalidass Mahalingam 5/2/2019
 */
public class UserItemWriterTest {
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
     * The User item writer.
     */
    @InjectMocks
    UserItemWriter userItemWriter;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test write.
     *
     * @throws Exception the exception
     */
    @Test
    public void testWrite() throws Exception {
        userItemWriter.write(Arrays.<UserStatus>asList(new UserStatus()));
    }
}

