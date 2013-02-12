package net.kaczmarzyk.examples.asyncio;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;

public class JettyClient implements Client {

	HttpClient httpClient = new HttpClient();

	public JettyClient() throws Exception {
		httpClient.setMaxConnectionsPerAddress(20000);
		httpClient.setExecutor(Executors.newFixedThreadPool(2));
		httpClient.start();
	}

	public void executeAndRegisterExecutor(final String url, final AtomicInteger counter, final Collection<String> threads) throws Exception {
		httpClient.newRequest(url).send(new Response.CompleteListener() {
			public void onComplete(Result result) {
				counter.incrementAndGet();
				threads.add(Thread.currentThread().getName());
			}
		});
	}

}
