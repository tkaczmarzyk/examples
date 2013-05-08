package net.kaczmarzyk.example;

import static net.kaczmarzyk.example.FestTest.NaturalNumberAssert.assertThat;
import static org.fest.assertions.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class FestTest {

	@Test
	public void collectionAsserts() {
		List<Integer> ints = Arrays.asList(1, 2, 3, 4);
		
		assertThat(ints).hasSize(4).doesNotHaveDuplicates();//.doesNotContain(1, 22);
	}
	
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
