package dao;

import model.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> getAllPerson();

    Person getPersonByID(Long id);

    List<Person> getPersonLikeName(String name);

    Long addPerson(Person person);

    void updatePerson(Person person);

    void removePersonByID(Long id);

}
