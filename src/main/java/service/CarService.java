package service;

import model.Car;
import model.Person;

import java.util.List;

public interface CarService {

    List<Car> getAllCar();

    Car getCarByID(Long id);

    Long addCar(Car car);

    void updateCar(Car car);

    void removeCarByID(Long id);

}
