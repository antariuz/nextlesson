package service.impl;

import dao.MSPersonDAO;
import dao.PersonDAO;
import model.Person;
import service.PersonService;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    public List<Person> addAndGetAllPerson(Person person) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.addPerson(person);
        return personDAO.getAllPerson();
    }

    public List<Person> getAllPerson() {
        PersonDAO personDAO = new MSPersonDAO();
        return personDAO.getAllPerson();
    }

    public void addPersonList(List<Person> list) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.getAllPerson().addAll(list);
    }

    public void updatePerson(Person person) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.updatePerson(person);
    }

    public void removePersonByID(Long id) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.removePersonByID(id);
    }

}
