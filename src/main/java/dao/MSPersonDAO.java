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

    public List<Person> getAllPerson() {
        List<Person> list = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM persons")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong(1));
                person.setName(resultSet.getString(2));
                person.setSurname(resultSet.getString(3));
                person.setAge(resultSet.getInt(4));
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // EXCEPTION of NULLPOINTER? if list goes null
        return list;
    }

    public Person getPersonById() {
        return null;
    }

    public Person getPersonByIdd(Long id) {
        Person person = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM persons WHERE id='" + id + "'")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person = new Person();
                person.setId(resultSet.getLong(1));
                person.setName(resultSet.getString(2));
                person.setSurname(resultSet.getString(3));
                person.setAge(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // EXCEPTION ? NULLPOINTER? if person goes null
        return person;
    }

    public Person getPersonLikeName(String name) {
        return null;
    }

    public Long addPerson(Person person) {
        Long id = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO persons(name, surname, age) VALUES(?,?,?)")) {
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
                     connection.prepareStatement("SELECT * FROM persons ORDER BY id DESC LIMIT 0, 1")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // EXCEPTION ? NULLPOINTER? if person goes null
        return id;
    }

    public void updatePerson(Person person) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("update persons set name = ?, surname = ?, age = ? where id = ?")) {
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

    public void removePersonById(Long personId) {
        //Check if the person with ID does exist ?
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM persons WHERE id='" + personId + "'")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
