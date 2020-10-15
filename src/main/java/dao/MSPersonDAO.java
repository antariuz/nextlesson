package dao;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSPersonDAO implements PersonDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/people" +
            "?autoReconnect=true" +
            "&useSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "32167";

    @Override
    public List<Person> getAllPerson() {
        List<Person> list = new ArrayList<>();
        PersonFactory personFactory = PersonFactory.getInstance();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM person");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            list = personFactory.createVOList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Person getPersonByID(Long id) {
        Person person = null;
        PersonFactory personFactory = PersonFactory.getInstance();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM person WHERE id='" + id + "'");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                person = personFactory.createPersonVO(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public List<Person> getPersonLikeName(String name) {
        List<Person> list = null;
        PersonFactory personFactory = PersonFactory.getInstance();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement
                     = connection.prepareStatement("SELECT * FROM person WHERE name LIKE '%" + name + "%'");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            list = personFactory.createVOList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Long addPerson(Person person) {
        Long id = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO person(name, surname, age) VALUES(?,?,?)")) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            if (person.getAge() == null || person.getAge() < 0) {
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, person.getAge());
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM person ORDER BY id DESC LIMIT 0, 1")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void updatePerson(Person person) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("update person set name = ?, surname = ?, age = ? where id = ?")) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            if (person.getAge() == null || person.getAge() < 0) {
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, person.getAge());
            }
            preparedStatement.setLong(4, person.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePersonByID(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM person WHERE id='" + id + "'")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
