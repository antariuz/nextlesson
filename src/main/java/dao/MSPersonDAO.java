package dao;

import model.Person;

import java.sql.*;
import java.util.List;

public class MSPersonDAO implements PersonDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/people" +
            "?autoReconnect=true" +
            "&useSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "32167";

    private static final int ID_INDEX = 1;
    private static final int NAME_INDEX = 2;
    private static final int SURNAME_INDEX = 3;
    private static final int AGE_INDEX = 4;
    private static final String INSERT_NEW = "INSERT INTO persons VALUES(?,?,?,?)";

    public List<Person> getAllPerson() {
        return null;
    }

    public Person getPersonById() {
        return null;
    }

    public Person getPersonLikeName(String name) {
        return null;
    }

    public Long addPerson(Person person) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW)) {
            //need check ID from table
            preparedStatement.setInt(ID_INDEX,person.getId());
            preparedStatement.setString(NAME_INDEX, person.getName());
            preparedStatement.setString(SURNAME_INDEX, person.getSurname());
            if (person.getAge() == null || person.getAge() < 0) {
                preparedStatement.setNull(AGE_INDEX, Types.INTEGER);
            } else {
                preparedStatement.setInt(AGE_INDEX,person.getAge());
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully added: " + person);
        return null;
    }

    public void updatePerson(Person person) {

    }

    public void removePersonById(Long personId) {

    }

}
