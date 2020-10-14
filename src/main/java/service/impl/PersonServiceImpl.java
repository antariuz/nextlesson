package service.impl;

import dao.MSPersonDAO;
import model.Person;
import service.PersonService;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    public List<Person> addAndGetAllPerson(Person person) {
        MSPersonDAO msPersonDAO = new MSPersonDAO();
        msPersonDAO.addPerson(person);
        return msPersonDAO.getAllPerson();
    }

    public void addPersonList(List<Person> list) {
        MSPersonDAO msPersonDAO = new MSPersonDAO();
        msPersonDAO.getAllPerson().addAll(list);
    }

    public void updatePerson(Person person) {
        MSPersonDAO msPersonDAO = new MSPersonDAO();
        msPersonDAO.updatePerson(person);
    }

    public void removePersonByID(Long id) {
        MSPersonDAO msPersonDAO = new MSPersonDAO();
        msPersonDAO.removePersonByID(id);
    }

}
