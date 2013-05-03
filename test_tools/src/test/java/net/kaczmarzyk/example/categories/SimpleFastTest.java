package net.kaczmarzyk.example.categories;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FastTest.class)
public class SimpleFastTest {

	@Test
	public void dummyTest() {
		assertTrue(true);
	}
}
