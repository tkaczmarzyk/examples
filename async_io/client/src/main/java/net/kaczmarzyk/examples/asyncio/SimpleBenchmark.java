package net.kaczmarzyk.examples.asyncio;

import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleBenchmark {

	public static void main(String... args) throws Exception {
		
		final AtomicInteger count = new AtomicInteger(0);
		final Set<String> tids = new HashSet<String>();
		
		int n = args.length > 0 ? Integer.parseInt(args[0]) : 1;
		
		Client client = new JettyClient();
		
		for (int i = 0; i < n; i++) {
			client.executeAndRegisterExecutor("http://localhost:8000/", count, tids);
		}
		
		Thread.sleep(3000);
		
		for (String tid : tids) {
			System.out.println(tid);
		}
		
		System.out.println();
		System.out.println("used threads: " + tids.size());
		System.out.println("active threads: " + ManagementFactory.getThreadMXBean().getThreadCount());
		System.out.println("processed: " + count.intValue());
		System.exit(0);
	}
}
