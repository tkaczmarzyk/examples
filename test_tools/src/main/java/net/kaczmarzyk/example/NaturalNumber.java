package net.kaczmarzyk.example;

@SuppressWarnings("serial")
public class NaturalNumber extends Number {

	private int value;
	
	public NaturalNumber(int num) {
		if (num < 0) {
			throw new IllegalArgumentException();
		}
		this.value = num;
	}
	
	public int getValue() {
		return value;
	}
	
	public NaturalNumber divide(int divisor) {
		if (value % divisor != 0) {
			throw new IllegalArgumentException("result is not natural!");
		}
		return new NaturalNumber(value / divisor);
	}

	@Override
	public double doubleValue() {
		return value;
	}

	@Override
	public float floatValue() {
		return value;
	}

	@Override
	public int intValue() {
		return value;
	}

	@Override
	public long longValue() {
		return value;
	}
}
