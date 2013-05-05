package net.kaczmarzyk.example;

public class Job extends Thread {
	
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