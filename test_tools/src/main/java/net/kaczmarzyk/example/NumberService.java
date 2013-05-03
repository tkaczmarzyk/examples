package net.kaczmarzyk.example;

import java.util.ArrayList;
import java.util.List;

public class NumberService {

	private List<Number> nums = new ArrayList<>();
	
	public void store(Number n) {
		nums.add(n);
	}
}
