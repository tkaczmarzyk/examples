package net.kaczmarzyk.examples.asyncio;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleBenchmark {

	public static void main(String... args) throws Exception {
		
		final AtomicInteger count = new AtomicInteger(0);
		final List<String> tids = new ArrayList<String>();
		
		int n = args.length > 0 ? Integer.parseInt(args[0]) : 1;
		
		Client client = new JettyClient();
		
		for (int i = 0; i < n; i++) {
			client.executeAndRegisterExecutor("http://localhost:8000/", count, tids);
		}
		
		Thread.sleep(10200);
		
		for (String tid : tids) {
			System.out.println(tid);
		}
		
		System.out.println();
		System.out.println("active threads: " + ManagementFactory.getThreadMXBean().getThreadCount());
		System.out.println("processed: " + count.intValue());
		System.exit(0);
	}
}
