package com.friendsurance.repository;

import com.friendsurance.dto.Schedule;

import java.util.List;

/**
 * The interface Scheduler repo.
 */
public interface SchedulerRepo  {
    /**
     * Gets scheduler by id.
     *
     * @param id the id
     * @return the scheduler by id
     */
    Schedule getSchedulerById(String id);

    /**
     * Synching new schedule list.
     *
     * @return the list
     */
    List<Schedule> synchingNewSchedule();

    /**
     * Update schedule boolean.
     *
     * @param schedule the schedule
     * @return the boolean
     */
    boolean updateSchedule(Schedule schedule);
}
