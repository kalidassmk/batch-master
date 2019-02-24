package com.friendsurance.controller;

import com.friendsurance.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Scheduler controller expose the scheduler end point /startSchedule
 */
@RestController
public class SchedulerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerController.class);

	/**
	 * The Schedule service.
	 */
	@Autowired
	ScheduleService scheduleService;

	/**
	 *
	 *
	 * Start scheduler end point.
	 *
	 * @return the boolean
	 */
	@RequestMapping("/startSchedule")
	public boolean startScheduler() {
		LOGGER.info("SchedulerActor start...................");
		return scheduleService.startScheduler();
	}

}
