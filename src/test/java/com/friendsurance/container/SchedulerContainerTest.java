package com.friendsurance.container;

import com.friendsurance.dto.Schedule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static org.mockito.Mockito.*;

/**
 * com.friendsurance.container
 *
 * @author Kalidass Mahalingam 5/2/2019
 */
public class SchedulerContainerTest {

    /**
     * The Cancellable schedule.
     */
    @Mock
    Map<String, ScheduledFuture> cancellableSchedule;
    /**
     * The Scheduler container.
     */
    @InjectMocks
    SchedulerContainer schedulerContainer;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test synching existing scheduler.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSynchingExistingScheduler() throws Exception {
        List<Schedule> result = schedulerContainer.synchingExistingScheduler();
    }

    /**
     * Test get.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGet() throws Exception {
        Schedule result = schedulerContainer.get("id");
    }

    /**
     * Test get cancellable schedule.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetCancellableSchedule() throws Exception {
        ScheduledFuture result = schedulerContainer.getCancellableSchedule("id");
    }

    /**
     * Test save.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSave() throws Exception {
        Schedule result = schedulerContainer.save(new Schedule());
    }

    /**
     * Test remove.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRemove() throws Exception {
        Schedule result = schedulerContainer.remove("id");
    }

    /**
     * Test remove cancellable schedule.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRemoveCancellableSchedule() throws Exception {
        ScheduledFuture result = schedulerContainer.removeCancellableSchedule("id");
    }

    /**
     * Test save cancellable schedule.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSaveCancellableSchedule() throws Exception {
        ScheduledFuture run = null;
        ScheduledFuture result = schedulerContainer.saveCancellableSchedule("id",run);
    }

    /**
     * Test get instance.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetInstance() throws Exception {
        SchedulerContainer result = SchedulerContainer.getInstance();
    }
}

