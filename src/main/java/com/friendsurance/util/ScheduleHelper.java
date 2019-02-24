package com.friendsurance.util;

import com.friendsurance.dto.Schedule;
import com.friendsurance.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.TimeZone;


/**
 * The type Schedule helper.
 */
public class ScheduleHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleHelper.class);

    /**
     * Next run sync long.
     *
     * @param schedule the schedule
     * @param nowCal   the now cal
     * @return the long
     * @throws AppException the app exception
     */
    public static long nextRunSync(Schedule schedule, Calendar nowCal) throws AppException {
        LOGGER.info("Calculate the next Sync..........." + schedule.toString());
        long durationInSec = 0;
        Calendar scheduleCal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
        String[] hourMin = schedule.getTime().split(":");
        int curMonth = nowCal.get(Calendar.MONTH);
        switch (schedule.getFrequency()) {
            case "daily":
                scheduleCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourMin[0]));
                scheduleCal.set(Calendar.MINUTE, Integer.parseInt(hourMin[1]));
                if (nowCal.getTimeInMillis() > scheduleCal.getTimeInMillis()) {
                    scheduleCal.add(Calendar.HOUR, 24);
                    durationInSec = (scheduleCal.getTimeInMillis() - nowCal.getTimeInMillis()) / 1000;
                } else if (nowCal.getTimeInMillis() < scheduleCal.getTimeInMillis()) {
                    durationInSec = (scheduleCal.getTimeInMillis() - nowCal.getTimeInMillis()) / 1000;
                } else {
                    durationInSec = 0;
                }
                break;
        }
        schedule.setNextRun(showTimein(scheduleCal));
        return durationInSec;

    }

    private static String showTimein(Calendar cal) {
        Calendar calendar = cal;
        StringBuilder _ret = new StringBuilder();

        if (cal == null) {
            calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
        }

        _ret.append(calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.YEAR) + " T " + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        return _ret.toString();
    }

}
