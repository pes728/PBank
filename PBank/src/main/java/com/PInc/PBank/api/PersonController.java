package com.PInc.PBank.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PInc.PBank.model.Person;
import com.PInc.PBank.service.PersonService;

@RequestMapping("api/people")
@RestController
public class PersonController {
	
	private final PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person) {
		personService.insertPerson(person);
	}
	
	@GetMapping
	public List<Person> getAllPeople(){
		return personService.getAllPeople();
	}
	
	@GetMapping(path = "/getById")
	public Person getPersonById(@RequestParam("id") UUID id) {
		return personService.getPersonByID(id).orElse(null);
	}
	
	@GetMapping(path = "/getByName")
	public Person getPersonByName(@RequestParam("name") String name) {
		return personService.getPersonByName(name).orElse(null);
	}
	
	
	@DeleteMapping
	public void deletePersonByID(@RequestParam("id") UUID id) {
		personService.deletePerson(id);
	}
	
	@PutMapping
	public void updatePerson(@RequestParam("id") UUID id, @RequestBody Person person) {
		personService.updatePerson(id, person);
	}
	
}
