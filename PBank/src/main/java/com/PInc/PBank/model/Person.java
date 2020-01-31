package com.PInc.PBank.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	private final UUID id;
	@NotBlank
	private final String name;

	public Person(Person person) {
		super();
		this.id = person.getId();
		this.name = person.getName().toLowerCase();
	}
	
	public Person(@JsonProperty("id") UUID id,
			@JsonProperty("name") String name) {
		super();
		this.id = id;
		this.name = name.toLowerCase();
	}
	
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}	
}
