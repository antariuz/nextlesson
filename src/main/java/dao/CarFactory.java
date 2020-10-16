package dao;

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
        carDTO.setModel(car.getModel());
        carDTO.setManufacturedYear(car.getManufacturedYear());
        return carDTO;
    }

    public Car createCarVO(CarDTO carDTO) {
        Car carVO = new Car();
        carVO.setId(carDTO.getId());
        carVO.setModel(carDTO.getModel());
        carVO.setManufacturedYear(carDTO.getManufacturedYear());
        return carVO;
    }

    public Car createCarVO(ResultSet resultSet) {
        Car carVO = new Car();
        try {
            while (resultSet.next()) {
                carVO.setId(resultSet.getLong("id"));
                carVO.setModel(resultSet.getString("model"));
                carVO.setManufacturedYear(resultSet.getInt("manufacturedYear"));
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
        return carVO;
    }

    public List<CarDTO> createCarDTOList(List<Car> listVO) {
        if (listVO == null) return new ArrayList<>();
        List<CarDTO> listDTO = new ArrayList<>();
        for (Car carVO : listVO) {
            CarDTO carDTO = new CarDTO();
            carVO.setId(carVO.getId());
            carVO.setModel(carVO.getModel());
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
                carVO.setId(resultSet.getLong("id"));
                carVO.setModel(resultSet.getString("model"));
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
            carVO.setModel(carDTO.getModel());
            carVO.setManufacturedYear(carDTO.getManufacturedYear());
            listVO.add(carVO);
        }
        return listVO;
    }

}
