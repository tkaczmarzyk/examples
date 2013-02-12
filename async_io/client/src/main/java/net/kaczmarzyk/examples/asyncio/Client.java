package net.kaczmarzyk.examples.asyncio;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public interface Client {

	public void executeAndRegisterExecutor(final String url, AtomicInteger counter, final List<String> threads) throws Exception;
}
