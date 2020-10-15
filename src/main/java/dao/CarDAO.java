package dao;

import model.Car;

import java.util.List;

public interface CarDAO {
    List<Car> getAllCar();
    Car getCarByID(Long id);
    Long addCar (Car car);
    void updateCar (Car car);
    void removeCarByID (Long id);
}
