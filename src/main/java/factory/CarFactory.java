package factory;

import model.Car;
import model.CarDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum CarFactory {
    INSTANCE;

    CarFactory() {
    }

    public static CarFactory getInstance() {
        return INSTANCE;
    }

    public CarDTO createCarDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setDriverID(car.getDriverID());
        carDTO.setModel(car.getModel());
        carDTO.setEngine(car.getEngine());
        carDTO.setManufacturedYear(car.getManufacturedYear());
        return carDTO;
    }

    public Car createCarVO(CarDTO carDTO) {
        Car carVO = new Car();
        carVO.setId(carDTO.getId());
        carVO.setDriverID(carDTO.getDriverID());
        carVO.setModel(carDTO.getModel());
        carVO.setEngine(carDTO.getEngine());
        carVO.setManufacturedYear(carDTO.getManufacturedYear());
        return carVO;
    }

    public Car createCarVO(ResultSet resultSet) {
        Car carVO = new Car();
        try {
            resultSet.next();
            carVO.setId(resultSet.getLong("car_id"));
            carVO.setDriverID(resultSet.getLong("driver_id"));
            carVO.setModel(resultSet.getString("model"));
            carVO.setEngine(resultSet.getString("engine"));
            carVO.setManufacturedYear(resultSet.getInt("manufacturedYear"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return carVO;
    }

    public List<CarDTO> createCarDTOList(List<Car> listVO) {
        if (listVO == null) return new ArrayList<>();
        List<CarDTO> listDTO = new ArrayList<>();
        for (Car carVO : listVO) {
            CarDTO carDTO = new CarDTO();
            carVO.setId(carVO.getId());
            carVO.setDriverID(carVO.getDriverID());
            carVO.setModel(carVO.getModel());
            carVO.setEngine(carVO.getEngine());
            carVO.setManufacturedYear(carVO.getManufacturedYear());
            listDTO.add(carDTO);
        }
        return listDTO;
    }

    public List<Car> createCarVOList(ResultSet resultSet) {
        List<Car> listVO = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Car carVO = new Car();
                carVO.setId(resultSet.getLong("car_id"));
                if (carVO.getDriverID() == null || carVO.getDriverID() == 0) {
                    carVO.setDriverID(null);
                } else {
                    carVO.setDriverID(resultSet.getLong("driver_id"));
                }
                carVO.setModel(resultSet.getString("model"));
                carVO.setEngine(resultSet.getString("engine"));
                carVO.setManufacturedYear(resultSet.getInt("manufacturedYear"));
                listVO.add(carVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listVO;
    }

    public List<Car> createCarVOList(List<CarDTO> listDTO) {
        if (listDTO == null) return null;
        List<Car> listVO = new ArrayList<>();
        for (CarDTO carDTO : listDTO) {
            Car carVO = new Car();
            carVO.setId(carDTO.getId());
            carVO.setDriverID(carDTO.getDriverID());
            carVO.setModel(carDTO.getModel());
            carVO.setEngine(carDTO.getEngine());
            carVO.setManufacturedYear(carDTO.getManufacturedYear());
            listVO.add(carVO);
        }
        return listVO;
    }

}