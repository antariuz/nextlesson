package dao.impl;

import dao.CarDAO;
import dao.PersonDAO;
import factory.CarFactory;
import model.Car;
import model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MSCarDAO implements CarDAO {

    private final Logger LOGGER = LogManager.getLogger(MSCarDAO.class.getName());
    private final String URL = dbConfigLoad().get(0);
    private final String USER = dbConfigLoad().get(1);
    private final String PASSWORD = dbConfigLoad().get(2);

    private List<String> dbConfigLoad() {
        Properties properties = new Properties();
        List<String> list = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(fileInputStream);
            list.add(properties.getProperty("db.url"));
            list.add(properties.getProperty("db.user"));
            list.add(properties.getProperty("db.password"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return list;
    }


    @Override
    public List<Car> getAllCar() {
        List<Car> list = new ArrayList<>();
        CarFactory carFactory = CarFactory.getInstance();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement =
                     connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM car")) {
            list = carFactory.createCarVOList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Car getCarByID(Long id) {
        Car car = null;
        CarFactory carFactory = CarFactory.getInstance();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM car WHERE car_id='" + id + "'");
            car = carFactory.createCarVO(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return car;
    }

    @Override
    public Person getDriverByID(Long id) {
        PersonDAO personDAO = new MSPersonDAO();
        personDAO.getPersonByID(id);
        return personDAO.getPersonByID(id);
    }

    @Override
    public Long addCar(Car car) {
        Long id = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO car(driver_id, model, engine, manufactured_year) " +
                             "VALUES(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            if (car.getDriverID() == null || car.getDriverID() == 0) {
                preparedStatement.setNull(1, Types.INTEGER);
            } else {
                preparedStatement.setLong(1, car.getDriverID());
            }
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getEngine());
            if (car.getManufacturedYear() == null || car.getManufacturedYear() < 1886) {
                preparedStatement.setNull(4, Types.INTEGER);
            } else {
                preparedStatement.setInt(4, car.getManufacturedYear());
            }
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return id;
    }

//    public Long addCar(Car car) {
//        Long id = null;
//        CarFactory carFactory = CarFactory.getInstance();
//        Car car = carFactory.createCarVO(carDTO);
//        try (Connection connection = ...

    @Override
    public void updateCar(Car car) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("update car set driver_id = ?, model = ?, engine = ?, " +
                             "manufactured_year = ? where car_id = ?")) {
            if (car.getDriverID() == null || car.getDriverID() == 0) {
                preparedStatement.setNull(1, Types.INTEGER);
            } else {
                preparedStatement.setLong(1, car.getDriverID());
            }
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getEngine());
            if (car.getManufacturedYear() == null || car.getManufacturedYear() < 1886) {
                preparedStatement.setNull(4, Types.INTEGER);
            } else {
                preparedStatement.setInt(4, car.getManufacturedYear());
            }
            preparedStatement.setLong(5, car.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void removeCarByID(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement =
                     connection.createStatement()) {
            statement.executeQuery("DELETE FROM car WHERE car_id='" + id + "'");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
