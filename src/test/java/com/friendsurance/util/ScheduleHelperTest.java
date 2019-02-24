package com.friendsurance.util;

import com.friendsurance.dto.Schedule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.TimeZone;

import static org.mockito.Mockito.*;

/**
 * com.friendsurance.util
 *
 * @author Kalidass Mahalingam 5/2/2019
 */
public class ScheduleHelperTest {
    /**
     * The Logger.
     */
    @Mock
    Logger LOGGER;
    /**
     * The Schedule helper.
     */
    @InjectMocks
    ScheduleHelper scheduleHelper;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test next run sync.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNextRunSync() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setId("1");
        schedule.setFrequency("daily");
        schedule.setFrequencyValues(1);
        schedule.setStatus("NEW");
        schedule.setTime("01:10");
        Calendar scheduleCal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
        long result = ScheduleHelper.nextRunSync(schedule, scheduleCal);
        Assert.assertEquals(true, result> 0);
    }
}

