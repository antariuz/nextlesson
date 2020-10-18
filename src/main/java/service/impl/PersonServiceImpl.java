package service.impl;

import dao.impl.MSPersonDAO;
import dao.PersonDAO;
import model.Person;
import service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Override
    public List<Person> addAndGetAllPerson(Person person) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.addPerson(person);
        return personDAO.getAllPerson();
    }

    @Override
    public void addPersonList(List<Person> list) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.getAllPerson().addAll(list);
    }

    @Override
    public void updatePerson(Person person) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.updatePerson(person);
    }

    @Override
    public void removePersonByID(Long id) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.removePersonByID(id);
    }

    public List<Person> getAllPerson() {
        PersonDAO personDAO = new MSPersonDAO();
        return personDAO.getAllPerson();
    }

}
