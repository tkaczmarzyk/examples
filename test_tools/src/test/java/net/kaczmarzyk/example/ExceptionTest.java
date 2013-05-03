package net.kaczmarzyk.example;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.CatchException.verifyException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExceptionTest {

	@Test
	public void classicTryCatch() {
		try {
			new NaturalNumber(-1);
			fail("IllegalArgumentException was expected!");
		} catch (IllegalArgumentException e) {
			// it's ok :-)
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void junitExpectedException() {
		new NaturalNumber(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void junitExpectedException_antiExample() {
		NaturalNumber n = new NaturalNumber(-2);
		
		n.divide(3); // unreachable code
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void junitRule() {
		exception.expect(IllegalArgumentException.class);

		new NaturalNumber(-1);
	}
	
	@Test
	public void catchExceptionLib() {
		NaturalNumber n = new NaturalNumber(2);
		
		catchException(n).divide(3);
		
		assertEquals(IllegalArgumentException.class, caughtException().getClass());
	}
	
	@Test
	public void catchExceptionLib_concise() {
		NaturalNumber n = new NaturalNumber(2);
		
		verifyException(n).divide(3);
		
		verifyException(n, IllegalArgumentException.class).divide(3);
	}
	
	@Test
	public void catchExceptionLib_bddStyle() {
		// given:
		NaturalNumber n = new NaturalNumber(2);
		
		when(n).divide(3);
		
		then(caughtException())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("result is not natural!")
			.hasNoCause();
	}
}
