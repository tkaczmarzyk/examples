package net.kaczmarzyk.example;

import static com.google.code.tempusfugit.temporal.Duration.seconds;
import static com.google.code.tempusfugit.temporal.Timeout.timeout;
import static com.google.code.tempusfugit.temporal.WaitFor.waitOrTimeout;
import static com.jayway.awaitility.Awaitility.*;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.*;

import org.junit.Test;

import com.google.code.tempusfugit.temporal.Condition;


public class AwaitTest {

	private static class Job extends Thread {
		
		private boolean done;
		
		@Override
		public void run() {
			try {
				Thread.sleep(750);
				done = true;
			} catch (Exception e) {
			}
		}
		
		public boolean isDone() {
			return done;
		}
	}
	
	@Test
	public void tempusFugitWaitFor() throws Exception {
		Job job = new Job();
		job.start();
		
		waitOrTimeout(isDone(job), timeout(seconds(2)));
	}
	
	private Condition isDone(final Job job) {
		return new Condition() {
			@Override
			public boolean isSatisfied() {
				return job.isDone();
			}
		};
	}

	@Test
	public void awaitility() {
		final Job job = new Job();
		job.start();
		
		await().atMost(2, SECONDS).until(done(job));
	}
	
	private Callable<Boolean> done(final Job job) {
		return status(job);
	}
	
	@Test
	public void awaitlity_withHamcrest() {
		final Job job = new Job();
		job.start();
		
		await().atMost(2, SECONDS).until(status(job), is(true));
	}

	private Callable<Boolean> status(final Job job) {
		return new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return job.isDone();
			}
		};
	}
	
	@Test
	public void awaitility_proxy() {
		Job job = new Job();
		job.start();
		
		await().atMost(2, SECONDS).untilCall(to(job).isDone(), equalTo(true));
	}
}
