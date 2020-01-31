package com.PInc.PBank.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.PInc.PBank.model.Person;

public interface PersonDao {
	
	int insertPerson(UUID id, Person person);
	
	default int insertPerson(Person person) {
		UUID id = UUID.randomUUID();
		return insertPerson(id, person);
	}

	List<Person> selectAllPeople();
	
	Optional<Person> selectPersonById(UUID id);
	
	Optional<Person> selectPersonByName(String name);
	
	int deletePersonById(UUID id);
	
	int updatePersonById(UUID id, Person person);

	List<String> selectPeopleBySubstr(String substr, int count);

	List<String> selectCountNames(int count);
	
}
