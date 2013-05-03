package net.kaczmarzyk.example;

import java.util.List;

public class TestBean {

	private String name;

	private List<String> nicknames;
	
	private NaturalNumber id;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getNicknames() {
		return nicknames;
	}

	public void setNicknames(List<String> nicknames) {
		this.nicknames = nicknames;
	}

	public NaturalNumber getId() {
		return id;
	}

	public void setId(NaturalNumber id) {
		this.id = id;
	}
}
