package com.friendsurance.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * com.friendsurance.exception
 *
 * @author Kalidass Mahalingam 5/2/2019
 */
public class AppExceptionTest {
    /**
     * The Backtrace.
     */
    @Mock
    Object backtrace;
    /**
     * The Cause.
     */
    @Mock
    Throwable cause;
    /**
     * The Suppressed sentinel.
     */
//Field stackTrace of type StackTraceElement - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    List<Throwable> SUPPRESSED_SENTINEL;
    /**
     * The Suppressed exceptions.
     */
    @Mock
    List<Throwable> suppressedExceptions;
    /**
     * The App exception.
     */
    @InjectMocks
    AppException appException;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test get message.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetMessage() throws Exception {
        appException = new AppException("Expected message");
        String result = appException.getMessage();
        Assert.assertEquals("Expected message", result);
    }
}

