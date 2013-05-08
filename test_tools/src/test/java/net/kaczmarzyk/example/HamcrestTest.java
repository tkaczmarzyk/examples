package net.kaczmarzyk.example;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

public class HamcrestTest {

	@Test
	public void customMatcher() {
		NaturalNumber n = new NaturalNumber(2);
		
		NaturalNumber result = n.divide(2);
		
		assertThat(result, is(natural(1)));
	}
	
	@Test
	public void collectionMatcherWithEmbeddedCustomOne() {
		List<NaturalNumber> nums = Arrays.asList(new NaturalNumber(1), new NaturalNumber(2), new NaturalNumber(3));
		
		assertThat(nums, hasSize(3));
		assertThat(nums, hasItem(natural(3)));		
	}
	
	@Test
	public void customMatcherInMockito() {
		NumberService service = mock(NumberService.class);
		
		service.store(new NaturalNumber(2));
		
		verify(service).store(argThat(is(natural(2))));
	}

	private Matcher<NaturalNumber> natural(final int value) {
		return new BaseMatcher<NaturalNumber>() {
			@Override
			public boolean matches(Object item) {
				if (item instanceof NaturalNumber) {
					return value == ((NaturalNumber) item).getValue();
				} else {
					return false;
				}
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("a natural number of value " + value);
			}
		};
	}
	
}
