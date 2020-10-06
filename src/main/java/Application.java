import dao.MSPersonDAO;
import model.Person;

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

        Person person = new Person.Builder()
                .withID(1)
                .withName("Sponge")
                .withSurname("Bob")
                .withAge(1)
                .build();
        MSPersonDAO msPersonDAO = new MSPersonDAO();
        msPersonDAO.addPerson(person);


    }
}
