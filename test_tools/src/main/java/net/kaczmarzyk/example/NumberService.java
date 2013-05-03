package net.kaczmarzyk.example;

import java.util.ArrayList;
import java.util.List;

public class NumberService {

	private List<Number> nums = new ArrayList<>();
	
	public synchronized void store(Number n) {
		nums.add(n);
	}

	public List<Number> getNumbers() {
		return new ArrayList<>(nums);
	}
}
