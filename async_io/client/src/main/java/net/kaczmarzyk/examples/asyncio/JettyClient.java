package net.kaczmarzyk.examples.asyncio;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;

public class JettyClient implements Client {

	HttpClient httpClient = new HttpClient();

	public JettyClient() throws Exception {
		httpClient.start();
	}

	public void executeAndRegisterExecutor(final String url, final AtomicInteger counter, final List<String> threads) throws Exception {
		httpClient.newRequest(url).send(new Response.CompleteListener() {
			public void onComplete(Result result) {
				counter.incrementAndGet();
				threads.add(Thread.currentThread().getName());
			}
		});
	}

}
