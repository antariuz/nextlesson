package service;

import model.Person;

import java.util.List;

public interface PersonService {

    List<Person> addAndGetAllPerson(Person person);

    List<Person> getAllPerson();

    void addPersonList(List<Person> list);

    void updatePerson(Person person);

    void removePersonByID(Long id);

    Person getDriverByID(Long id);

}
