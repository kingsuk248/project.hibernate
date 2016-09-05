package com.practice.self.project.hibernate;

public class Project {
	private int id;
	private String name;
	private String vendor;
	
	public Project() {}
	
	public Project(String name, String vendor) {
		this.name = name;
		this.vendor = vendor;
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

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
}
