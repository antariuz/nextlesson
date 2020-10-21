package dao.impl;

import db.MySQL;
import dao.PersonDAO;
import factory.PersonFactory;
import model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSPersonDAO implements PersonDAO {

    private final Logger LOGGER = LogManager.getLogger(MSPersonDAO.class.getName());

    @Override
    public List<Person> getAllPerson() {
        List<Person> list = new ArrayList<>();
        PersonFactory personFactory = PersonFactory.getInstance();
        try (Connection connection = new MySQL().getConnection();
             Statement statement =
                     connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM person")) {
            list = personFactory.createVOList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Person getPersonByID(Long id) {
        Person person = null;
        PersonFactory personFactory = PersonFactory.getInstance();
        try (Connection connection = new MySQL().getConnection();
             Statement statement =
                     connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE person_id='" + id + "'")) {
            person = personFactory.createPersonVO(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return person;
    }

    @Override
    public List<Person> getPersonLikeName(String name) {
        List<Person> list = null;
        PersonFactory personFactory = PersonFactory.getInstance();
        try (Connection connection = new MySQL().getConnection();
             Statement statement
                     = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE name LIKE '%" + name + "%'")) {
            list = personFactory.createVOList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Long addPerson(Person person) {
        Long id = null;
        try (Connection connection = new MySQL().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO person(name, surname, age) VALUES(?,?,?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            if (person.getAge() == null || person.getAge() < 0) {
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, person.getAge());
            }
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return id;
    }

    @Override
    public void updatePerson(Person person) {
        try (Connection connection = new MySQL().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("update person set name = ?, surname = ?, age = ? where person_id = ?")) {
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
            LOGGER.error(e);
        }
    }

    @Override
    public void removePersonByID(Long id) {
        try (Connection connection = new MySQL().getConnection();
             Statement statement =
                     connection.createStatement()) {
            statement.executeQuery("DELETE FROM person WHERE person_id='" + id + "'");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
