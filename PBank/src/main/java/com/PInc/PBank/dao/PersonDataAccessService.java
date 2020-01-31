package com.PInc.PBank.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.PInc.PBank.model.Person;


@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public int insertPerson(UUID id, Person person) {
		final String sql = "INSERT INTO person(id, name) VALUES(?, ?)";
		return jdbcTemplate.update(sql, id, person.getName());
	}

	@Override
	public List<Person> selectAllPeople() {
		final String sql = "SELECT id, name FROM person";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			 return new Person(UUID.fromString(resultSet.getString("id")), 
					 resultSet.getString("name"));
		});
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		final String sql = "SELECT id, name FROM person WHERE id = ?";
		Person p = jdbcTemplate.queryForObject(sql, new Object[] {id},(resultSet, i) -> {
			return new Person(UUID.fromString(resultSet.getString("id")), 
					resultSet.getString("name"));
		});
		
		return Optional.ofNullable(p);
	}
	
	@Override
	public List<String> selectPeopleBySubstr(String substr, int count) {
		final String sql = "SELECT name FROM person WHERE name LIKE ? EXCEPT SELECT name FROM person WHERE name = ? LIMIT ?";
		String string = '%' + substr + '%';
		return jdbcTemplate.query(sql, new Object[] {string, substr, count},(resultSet, i) -> {
			return resultSet.getString("name");
		});	
	}
	
	@Override
	public List<String> selectCountNames(int count) {
		final String sql = "SELECT name FROM person LIMIT ?";
		return jdbcTemplate.query(sql, new Object[] {count}, (resultSet, i) -> {
			 return resultSet.getString("name");
		});
		
	}
	
	
	@Override
	public Optional<Person> selectPersonByName(String name) {
		final String sql = "SELECT id, name FROM person WHERE name = ?";
		Person p = jdbcTemplate.queryForObject(sql, new Object[] {name}, (resultSet, i) ->{
			return new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name"));
		});	
		return Optional.ofNullable(p);
	}
		
	@Override
	public int deletePersonById(UUID id) {
		final String sql = "DELETE FROM person WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		final String sql = "UPDATE person SET id = ?, name = ? WHERE id = ?";
		return jdbcTemplate.update(sql, person.getId(), person.getName(), id);
	}
}
