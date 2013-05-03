package net.kaczmarzyk.example;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;
import org.junit.Test;
import static net.kaczmarzyk.example.FestTest.NaturalNumberAssert.*;

public class FestTest {

	@Test
	public void customFestAssert() {
		NaturalNumber n = new NaturalNumber(2);
		
		assertThat(n).hasValue(2);
	}
	
	public static class NaturalNumberAssert extends AbstractAssert<NaturalNumberAssert, NaturalNumber> {

		protected NaturalNumberAssert(NaturalNumber actual, Class<?> selfType) {
			super(actual, selfType);
		}

		public NaturalNumberAssert hasValue(int expectedValue) {
			isNotNull();
			Assertions.assertThat(actual.getValue())
				.overridingErrorMessage("Expected natural number's value to be <%s> but was <%s> ", expectedValue, actual.getValue())
				.isEqualTo(expectedValue);
			
			return this;
		}
		
		public static NaturalNumberAssert assertThat(NaturalNumber n) {
			return new NaturalNumberAssert(n, NaturalNumberAssert.class);
		}
	}
}
