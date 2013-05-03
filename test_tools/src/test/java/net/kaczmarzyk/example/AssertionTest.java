package net.kaczmarzyk.example;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AssertionTest {

	private List<String> abc = Arrays.asList("a", "b", "c");
	
	@Test
	public void classicAssert() {
		assertEquals(3, abc.size());
	}
	
	@Test
	public void hamcrestMatcher() {
		assertThat(abc, hasSize(3));
	}
	
	@Test
	public void festAssert() {
		assertThat(abc).hasSize(3);
	}
}
