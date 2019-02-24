package com.friendsurance.service.impl;

import com.friendsurance.actor.SchedulerActor;
import com.friendsurance.container.SchedulerContainer;
import com.friendsurance.dto.Schedule;
import com.friendsurance.mail.EmailService;
import com.friendsurance.reader.ItemReader;
import com.friendsurance.repository.SchedulerRepo;
import com.friendsurance.service.ScheduleService;
import com.friendsurance.util.ScheduleHelper;
import com.friendsurance.writer.ItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * The type Scheduler service.
 *
 * @author Kalidass Mahalingam
 */
@Service("ScheduleService")
public class SchedulerServiceImpl implements ScheduleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	/**
	 * The Scheduler repo.
	 */
	@Autowired
	SchedulerRepo schedulerRepo;

	/**
	 * The Is started.
	 */
	public boolean isStarted = false;

	/**
	 * The May interrupt if running.
	 */
	boolean mayInterruptIfRunning = true;

	/**
	 * The Item reader.
	 */
	@Autowired
	ItemReader itemReader;

	/**
	 * The Item writer.
	 */
	@Autowired
	ItemWriter itemWriter;

	/**
	 * The Email service.
	 */
	@Autowired
	EmailService emailService;

	public boolean startScheduler() {

		if (!isStarted) {
			ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
			Runnable task = () -> {
				startScheduleSync();
			};
			executorService.scheduleWithFixedDelay(task, 0, 5, TimeUnit.MINUTES);
			isStarted = true;
		}
		LOGGER.info("Scheduler successfully started .......................");
		return true;
	}

	private boolean startScheduleSync() {

		LOGGER.info("Start Schedule Sync..................");

		List<Schedule> existingScheduleList = SchedulerContainer.getInstance().synchingExistingScheduler();
		existingScheduleList.stream().forEach(schedule -> {
			Schedule scheduleFromDb = schedulerRepo.getSchedulerById(schedule.getId());
			LOGGER.info("Remove the inactive Scheduler from Sync..................");
			if ("INACTIVE".equals(scheduleFromDb.getStatus())) {
				removeSchedule(schedule);
			}
		});

		List<Schedule> newSchedule = schedulerRepo.synchingNewSchedule();
		newSchedule.stream().forEach(schedule -> {
			if (SchedulerContainer.getInstance().get(schedule.getId()) != null) {
				if (removeSchedule(schedule)) {
					if (addSchedule(schedule)) {
						SchedulerContainer.getInstance().save(schedule);
					}
				}
			} else {
				if (addSchedule(schedule)) {
					SchedulerContainer.getInstance().save(schedule);
				}
			}
		});

		LOGGER.info("startScheduleSync");

		return false;
	}

	private boolean addSchedule(Schedule schedule) {

		try {
			ScheduledFuture cancellable;
			Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
			long delayInSec = ScheduleHelper.nextRunSync(schedule, currentTime);
			schedule.setDelay("" + delayInSec);
			LOGGER.info("Schedule frequency............" + schedule.getFrequency());
			ScheduledExecutorService actorSystem = Executors.newScheduledThreadPool(1);
			SchedulerActor schedulerActor = new SchedulerActor(schedule,  itemReader, itemWriter,emailService);

			if ("daily".equals(schedule.getFrequency())) {
				LOGGER.info("Scheduler name : " + schedule.getFrequency());
				LOGGER.info("Delay in seconds............" + delayInSec);
				cancellable = actorSystem.scheduleAtFixedRate(schedulerActor, delayInSec, 24 * 60 * 60, TimeUnit.SECONDS);
				SchedulerContainer.getInstance().saveCancellableSchedule(schedule.getId(), cancellable);
			}else{
				//implement next future
			}
			schedule.setStatus("ACTIVE");
			schedule.setLastTriggeredOn(new Timestamp(System.currentTimeMillis()));

			LOGGER.info("Update the Scheduler status........................");
			schedulerRepo.updateSchedule(schedule);
		} catch (Exception e) {
			LOGGER.error("Exception while Schedule Job........................");
			return false;
		}
		return true;
	}

	private boolean removeSchedule(Schedule schedule) {
		String id = schedule.getId();
		ScheduledFuture cancellable = SchedulerContainer.getInstance().getCancellableSchedule(id);
		if (cancellable != null) {
			cancellable.cancel(mayInterruptIfRunning);
			LOGGER.info("Successfully cancel the scheduler, Scheduler id: " + id);
		}
		SchedulerContainer.getInstance().remove(id);
		SchedulerContainer.getInstance().removeCancellableSchedule(id);
		return true;
	}

}
