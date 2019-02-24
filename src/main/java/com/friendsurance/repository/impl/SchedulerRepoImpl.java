package com.friendsurance.repository.impl;

import com.friendsurance.controller.SchedulerController;
import com.friendsurance.dto.Schedule;
import com.friendsurance.repository.SchedulerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Scheduler repo.
 */
@Repository("SchedulerRepo")
public class SchedulerRepoImpl implements SchedulerRepo {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerController.class);

    private static Map<String, Schedule> scheduleMap = new HashMap<String, Schedule>();
    private static List<Schedule> scheduleList = new ArrayList<>();

    private static final String SCHEDULER_QUERY = "SELECT * FROM SCHEDULER";

    @Override
    public Schedule getSchedulerById(String id) {
        LOGGER.info("Get the scheduler by schedule id................" + id);
        return scheduleMap.get("1");
    }

    @Override
    public List<Schedule> synchingNewSchedule() {
        try {
            return jdbcTemplateObject.query(SCHEDULER_QUERY, new SchedulerRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            LOGGER.error("Exception while get the scheduler data", e);
        }
        return null;
    }

    @Override
    public boolean updateSchedule(Schedule schedule) {
        LOGGER.info("Updated the scheduler by schedule................");
        scheduleMap.put(schedule.getId(), schedule);
        return true;
    }

    /**
     * The type Scheduler row mapper.
     */
    public static class SchedulerRowMapper implements RowMapper {

        static final String ID = "ID";
        static final String DELAY = "DELAY";
        static final String FREQUENCY_VALUE = "FREQUENCY_VALUE";
        static final String FREQUENCY = "FREQUENCY";
        static final String TIME = "TIME";

        static final String DESCRIPTION = "DESCRIPTION";
        static final String NAME = "NAME";
        static final String NEXT_RUN = "NEXT_RUN";
        static final String START_DATE = "START_DATE";
        static final String START_DAY = "START_DAY";

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getString(ID));
            schedule.setDelay(rs.getString(DELAY));
            schedule.setFrequencyValues(rs.getInt(FREQUENCY_VALUE));
            schedule.setFrequency(rs.getString(FREQUENCY));
            schedule.setTime(rs.getString(TIME));
            schedule.setDescription(rs.getString(DESCRIPTION));
            schedule.setName(rs.getString(NAME));
            schedule.setNextRun(rs.getString(NEXT_RUN));
            schedule.setStartDate(rs.getDate(START_DATE));
            schedule.setStartDay(rs.getString(START_DAY));
            return schedule;
        }
    }


}
