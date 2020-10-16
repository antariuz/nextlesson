package dao;

import model.Car;
import model.CarDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSCarDAO implements CarDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/car" +
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
                     connection.prepareStatement("SELECT * FROM car WHERE id='" + id + "'");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            car = carFactory.createCarVO(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public Long addCar(Car car) {
        Long id = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO car(model, manufacturedYear) VALUES(?,?)");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getManufacturedYear());
            if (car.getManufacturedYear() == null || car.getManufacturedYear() < 1886) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, car.getManufacturedYear());
            }
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM car ORDER BY id DESC LIMIT 0, 1")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Long addCar(CarDTO carDTO) {
        Long id = null;
        CarFactory carFactory = CarFactory.getInstance();
        Car car = carFactory.createCarVO(carDTO);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO car(model, manufacturedYear) VALUES(?,?)");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getManufacturedYear());
            if (carDTO.getManufacturedYear() == null || car.getManufacturedYear() < 1886) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, car.getManufacturedYear());
            }
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM car ORDER BY id DESC LIMIT 0, 1")) {
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
    public void updateCar(Car car) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("update car set model = ?, manufacturedYear = ? where id = ?")) {
            preparedStatement.setString(1, car.getModel());
            if (car.getManufacturedYear() == null || car.getManufacturedYear() < 1886) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, car.getManufacturedYear());
            }
            preparedStatement.setLong(4, car.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCarByID(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM car WHERE id='" + id + "'")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
