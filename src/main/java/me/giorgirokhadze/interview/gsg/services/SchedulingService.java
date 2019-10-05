package me.giorgirokhadze.interview.gsg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulingService {

	private final Map<Long, ScheduledFuture<?>> scheduledJobs = new HashMap<>();

	private final TaskScheduler executor;

	@Autowired
	public SchedulingService(TaskScheduler executor) {
		this.executor = executor;
	}

	public void schedule(final Runnable task, final Integer minutes, final Long userId) {
		if (scheduledJobs.containsKey(userId)) {
			ScheduledFuture<?> scheduledFuture = scheduledJobs.get(userId);
			scheduledFuture.cancel(true);
		}
		scheduledJobs.put(userId, executor.scheduleAtFixedRate(task, minutes * 60_000));
	}
}
