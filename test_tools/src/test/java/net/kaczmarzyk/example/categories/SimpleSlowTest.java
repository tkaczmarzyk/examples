package net.kaczmarzyk.example.categories;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;


@Category(SlowTest.class)
public class SimpleSlowTest {

	@Test
	public void dummyTest() {
		assertTrue(true); // not so slow at all :P
	}
}
