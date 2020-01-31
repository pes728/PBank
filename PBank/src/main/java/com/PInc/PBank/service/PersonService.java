package com.PInc.PBank.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.PInc.PBank.dao.PersonDao;
import com.PInc.PBank.model.Person;

@Service
public class PersonService {
	
	private final PersonDao personDao;
	
	@Autowired
	private PersonService(@Qualifier("postgres")PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public int insertPerson(Person person) {
		return personDao.insertPerson(new Person(person));
	}
	
	public List<Person> getAllPeople(){
		return personDao.selectAllPeople();
	}
	
	public Optional<Person> getPersonByID(UUID id){
		return personDao.selectPersonById(id);
	}
	
	public Optional<Person> getPersonByName(String name) {
		return personDao.selectPersonByName(name);
	}
	
	public int deletePerson(UUID id) {
		return personDao.deletePersonById(id);
	}
	
	public int updatePerson(UUID id, Person person) {
		return personDao.updatePersonById(id, new Person(person));
	}

	public List<String> getPeopleBySubstr(String substr, int count) {
		if(substr.isEmpty()) {
			return personDao.selectCountNames(count);
		}
		return personDao.selectPeopleBySubstr(substr, count);
	}

	
	
}
