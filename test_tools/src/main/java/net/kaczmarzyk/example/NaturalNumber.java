package net.kaczmarzyk.example;

public class NaturalNumber {

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
}
