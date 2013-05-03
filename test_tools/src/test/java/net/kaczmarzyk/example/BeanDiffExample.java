package net.kaczmarzyk.example;

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
		
		a2 = new TestBean();
		a2.setName("a2");
		a2.setId(new NaturalNumber(2));
		a2.setNicknames(Arrays.asList("a", "A", "22"));
	}
	
	@Ignore
	@Test
	public void test() {
		BeanDiffAssert.assertNoDifference(a1, a2);
	}
}
