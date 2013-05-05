package net.kaczmarzyk.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.beandiff.test.BeanDiffAssert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BeanDiffExample {

	private TestBean a1;
	private TestBean a2;
	
	@Before
	public void init() {
		a1 = new TestBean();
		a1.setName("a1");
		a1.setId(new NaturalNumber(1));
		a1.setNicknames(Arrays.asList("a", "A", "11"));
		TestBean a1Delegate = new TestBean();
		a1Delegate.setName("delegate");
		a1Delegate.setId(new NaturalNumber(3));
		a1Delegate.setNicknames(Arrays.asList("d1", "d2", "d3"));
		a1.setDelegate(a1Delegate);
		
		a2 = new TestBean();
		a2.setName("a2");
		a2.setId(new NaturalNumber(2));
		a2.setNicknames(Arrays.asList("a", "A", "22"));
		TestBean a2Delegate = new TestBean();
		a2Delegate.setName("delegate");
		a2Delegate.setId(new NaturalNumber(3));
		a2Delegate.setNicknames(Arrays.asList("d1", "d2", "d3", "d4"));
		a2.setDelegate(a2Delegate);
	}
	
//	@Ignore
	@Test
	public void findDifference() {
		assertEquals(a1, a2);
	}
	
//	@Ignore
	@Test
	public void findDifference_withBeandiff() {
		BeanDiffAssert.assertNoDifference(a1, a2);
	}
}
