package com.friendsurance.controller;

import com.friendsurance.service.ScheduleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

/**
 * com.friendsurance.controller
 *
 * @author Kalidass Mahalingam 5/2/2019
 */
public class SchedulerControllerTest {
    /**
     * The Logger.
     */
    @Mock
    Logger LOGGER;
    /**
     * The Schedule service.
     */
    @Mock
    ScheduleService scheduleService;
    /**
     * The Scheduler controller.
     */
    @InjectMocks
    SchedulerController schedulerController;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test start scheduler.
     *
     * @throws Exception the exception
     */
    @Test
    public void testStartScheduler() throws Exception {
        when(scheduleService.startScheduler()).thenReturn(true);
        boolean result = schedulerController.startScheduler();
        Assert.assertEquals(true, result);
    }
}

