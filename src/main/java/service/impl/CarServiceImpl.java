package service.impl;

import dao.CarDAO;
import dao.MSCarDAO;
import model.Car;
import service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getAllCar() {
        CarDAO carDAO = new MSCarDAO();
        return carDAO.getAllCar();
    }

    @Override
    public Car getCarByID(Long id) {
        CarDAO carDAO = new MSCarDAO();
        return carDAO.getCarByID(id);
    }

    @Override
    public Long addCar(Car car) {
        CarDAO carDAO = new MSCarDAO();
        return carDAO.addCar(car);
    }

    @Override
    public void updateCar(Car car) {
        CarDAO carDAO = new MSCarDAO();
        carDAO.updateCar(car);
    }

    @Override
    public void removeCarByID(Long id) {
        CarDAO carDAO = new MSCarDAO();
        carDAO.removeCarByID(id);
    }

}