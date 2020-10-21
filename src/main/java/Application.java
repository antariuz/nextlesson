import model.Car;
import service.CarService;
import service.impl.CarServiceImpl;

public class Application {

    public static void main(String[] args) {
        Car car = new Car();
        car.setDriverID(null);
        car.setModel("Bro_mobile");
        car.setEngine("Diesel");
        car.setManufacturedYear(2020);
        CarService carService = new CarServiceImpl();
        System.out.println(carService.addCar(car));
    }

}
