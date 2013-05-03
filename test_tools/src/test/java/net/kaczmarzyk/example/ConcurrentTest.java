package net.kaczmarzyk.example;

import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.*;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

public class ConcurrentTest {

	@Rule
	public ConcurrentRule concurrency = new ConcurrentRule();
	
	@Rule
	public RepeatingRule repeating = new RepeatingRule();
	
	private Random rand = new Random();
	
	private NumberService service = new NumberService();
	
	@Test
	@Concurrent(count = 10)
	@Repeating(repetition = 100)
	public void test() {
		NaturalNumber n = new NaturalNumber(Math.abs(rand.nextInt()));
		service.store(n);
		
		assertThat(service.getNumbers()).contains(n);
	}
}
