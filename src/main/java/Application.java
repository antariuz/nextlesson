import dao.MSPersonDAO;

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
        MSPersonDAO msPersonDAO = new MSPersonDAO();

//        Person person = new Person.Builder()
//
//                .withName("sPongeMan")
//                .withSurname("Klichko")
//                .withAge(444)
//                .build();

//        msPersonDAO.addPerson(person);
//        msPersonDAO.getPersonByIdd(4L);
//        msPersonDAO.removePersonById(5L);
        msPersonDAO.getAllPerson();
//        msPersonDAO.updatePerson(person);
//        System.out.println(msPersonDAO.getPersonLikeName("Sponge"));


    }
}
