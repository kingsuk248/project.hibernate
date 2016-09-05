package com.practice.self.project.hibernate.annotations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Dog")
public class Dog {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "dog_breed")
	private String breed;
	
	@Column(name = "dog_name")
	private String name;
	
	public Dog() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
