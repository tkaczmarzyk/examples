package net.kaczmarzyk.examples.asyncio;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;


public interface Client {

	public void executeAndRegisterExecutor(final String url, AtomicInteger counter, final Collection<String> threads) throws Exception;
}
