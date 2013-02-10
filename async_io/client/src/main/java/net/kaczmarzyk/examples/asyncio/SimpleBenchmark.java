package net.kaczmarzyk.examples.asyncio;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class SimpleBenchmark {

	public static void main(String... args) throws Exception {
		
		final AtomicInteger count = new AtomicInteger(0);
		final List<String> tids = new ArrayList<String>();
		
		int n = args.length > 0 ? Integer.parseInt(args[0]) : 1;
		
		for (int i = 0; i < n; i++) {
			AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
			asyncHttpClient.prepareGet("http://localhost:8000").execute(
					new AsyncCompletionHandler<Object>() {
	
						@Override
						public Response onCompleted(Response response) throws Exception {
							count.incrementAndGet();
							tids.add(Thread.currentThread().getName());
							return response;
						}
	
						@Override
						public void onThrowable(Throwable t) {
							// Something wrong happened.
						}
					});
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
