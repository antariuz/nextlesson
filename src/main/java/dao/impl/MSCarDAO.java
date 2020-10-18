package dao.impl;

import dao.CarDAO;
import dao.PersonDAO;
import dao.factory.CarFactory;
import model.Car;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSCarDAO implements CarDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/people" +
            "?autoReconnect=true" +
            "&useSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "32167";

    @Override
    public List<Car> getAllCar() {
        List<Car> list = new ArrayList<>();
        CarFactory carFactory = CarFactory.getInstance();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM car");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            list = carFactory.createCarVOList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Car getCarByID(Long id) {
        Car car = null;
        CarFactory carFactory = CarFactory.getInstance();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM car WHERE car_id='" + id + "'");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            car = carFactory.createCarVO(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
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
                     connection.prepareStatement("INSERT INTO car(driver_id, model, engine, manufacturedYear) " +
                             "VALUES(?,?,?,?)");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setLong(1, car.getDriverID());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getEngine());
            if (car.getManufacturedYear() == null || car.getManufacturedYear() < 1886) {
                preparedStatement.setNull(4, Types.INTEGER);
            } else {
                preparedStatement.setInt(4, car.getManufacturedYear());
            }
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM car ORDER BY car_id DESC LIMIT 0, 1")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("car_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

//    public Long addCar(Car car) {
//        Long id = null;
//        CarFactory carFactory = CarFactory.getInstance();
//        Car car = carFactory.createCarVO(carDTO);
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement preparedStatement =
//                     connection.prepareStatement("INSERT INTO car(driver_id, model, engine, manufacturedYear) " +
//                             "VALUES(?,?,?,?)");
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            preparedStatement.setLong(1, car.getDriverID());
//            preparedStatement.setString(2, car.getModel());
//            preparedStatement.setObject(3, car.getEngine());
//            if (carDTO.getManufacturedYear() == null || car.getManufacturedYear() < 1886) {
//                preparedStatement.setNull(4, Types.INTEGER);
//            } else {
//                preparedStatement.setInt(4, car.getManufacturedYear());
//            }
//            preparedStatement.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement preparedStatement =
//                     connection.prepareStatement("SELECT * FROM car ORDER BY car_id DESC LIMIT 0, 1")) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                id = resultSet.getLong("car_id");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }

    @Override
    public void updateCar(Car car) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("update car set driver_id = ?, model = ?, engine = ?, " +
                             "manufacturedYear = ? where car_id = ?")) {
            preparedStatement.setLong(1, car.getDriverID());
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
            e.printStackTrace();
        }
    }

    @Override
    public void removeCarByID(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM car WHERE car_id='" + id + "'")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}