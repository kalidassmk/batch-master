package com.friendsurance.container;

import com.friendsurance.dto.Schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

/**
 * The Scheduler container holds the Scheduler information .
 */
public class SchedulerContainer {

	private Map<String, Schedule> schedulerContainer = new HashMap<>();
	private Map<String, ScheduledFuture> cancellableSchedule = new HashMap<>();

	/**
	 * Synching existing scheduler list.
	 *
	 * @return the list
	 */
	public List<Schedule> synchingExistingScheduler() {
		return schedulerContainer.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList());
	}

	/**
	 * Get schedule.
	 *
	 * @param id the id
	 * @return the schedule
	 */
	public Schedule get(String id) {
		return schedulerContainer.get(id);
	}

	/**
	 * Gets cancellable schedule.
	 *
	 * @param id the id
	 * @return the cancellable schedule
	 */
	public ScheduledFuture getCancellableSchedule(String id) {
		return cancellableSchedule.get(id);
	}

	/**
	 * Save schedule.
	 *
	 * @param schedule the schedule
	 * @return the schedule
	 */
	public Schedule save(Schedule schedule) {
		return schedulerContainer.put(schedule.getId(), schedule);
	}

	/**
	 * Remove schedule.
	 *
	 * @param id the id
	 * @return the schedule
	 */
	public Schedule remove(String id) {
		return schedulerContainer.remove(id);
	}

	/**
	 * Remove cancellable schedule scheduled future.
	 *
	 * @param id the id
	 * @return the scheduled future
	 */
	public ScheduledFuture removeCancellableSchedule(String id) {
		return cancellableSchedule.remove(id);
	}

	/**
	 * Save cancellable schedule scheduled future.
	 *
	 * @param id          the id
	 * @param cancellable the cancellable
	 * @return the scheduled future
	 */
	public ScheduledFuture saveCancellableSchedule(String id, ScheduledFuture cancellable) {
		return cancellableSchedule.put(id, cancellable);
	}

	/**
	 * The type Instance helper.
	 */
	public static class InstanceHelper {
		private static SchedulerContainer schedulerContainer = new SchedulerContainer();
	}


	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static SchedulerContainer getInstance() {
		return InstanceHelper.schedulerContainer;
	}
}
