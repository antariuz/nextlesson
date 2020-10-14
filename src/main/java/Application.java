import dao.MSPersonDAO;
import model.Person;
import service.impl.PersonServiceImpl;

import java.sql.*;



public class Application {

    private static void driverJBDCInitialization() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        driverJBDCInitialization();
        PersonServiceImpl personService = new PersonServiceImpl();

        Person person = new Person.Builder()
                .withName("Vladimir")
                .withSurname("Zelenskiy")
                .withAge(0)
                .build();

        personService.addAndGetAllPerson(person);

    }
}
