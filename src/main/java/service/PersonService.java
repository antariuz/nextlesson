package service;

import model.Person;

import java.util.List;

public interface PersonService {
    List<Person> addAndGetAllPerson(Person person);
    void addPersonList(List<Person> personList);
    void updatePerson (Person person);
    void removePersonById (Long personId);
}
