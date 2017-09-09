package hello.dao;

import java.util.List;

import hello.model.Person;

public interface PersonDao {

	public void save(Person p);
	public List<Person> list();
}
