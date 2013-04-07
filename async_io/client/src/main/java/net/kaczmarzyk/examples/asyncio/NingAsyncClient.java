package net.kaczmarzyk.examples.asyncio;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class NingAsyncClient implements Client {

	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	
	public void executeAndRegisterExecutor(final String url, final AtomicInteger counter, final Collection<String> tids) throws Exception {
		asyncHttpClient.prepareGet(url).execute(
				new AsyncCompletionHandler<Object>() {
					@Override
					public Response onCompleted(Response response) throws Exception {
						counter.incrementAndGet();
						tids.add(Thread.currentThread().getName());
						return response;
					}

					@Override
					public void onThrowable(Throwable t) {
						// Something wrong happened.
					}
				});
	}

}
