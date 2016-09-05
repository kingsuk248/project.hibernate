package com.practice.self.project.hibernate;

public class Achievement {
	private int id;
	private String name;
	
	public Achievement() {}
	
	public Achievement(String name) {
	      this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
