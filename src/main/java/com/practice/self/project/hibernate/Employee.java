package com.practice.self.project.hibernate;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private int salary;
	private SortedSet<Certificate> certificates;
	private List<Achievement> achievements;
	private Map<String, Skill> skills;
	private Address address;;
	private Project project;
	
	public Employee() {}
	
	public Employee(String firstName, String lastName, int salary, Address address, Project project) {
		this.salary = salary;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.project = project;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public SortedSet<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(SortedSet<Certificate> certificates) {
		this.certificates = certificates;
	}

	public List<Achievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}

	public Map<String, Skill> getSkills() {
		return skills;
	}

	public void setSkills(Map<String, Skill> skills) {
		this.skills = skills;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
