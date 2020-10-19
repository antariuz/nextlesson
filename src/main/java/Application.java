import service.CarService;
import service.impl.CarServiceImpl;

public class Application {

    public static void main(String[] args) {

        CarService carService = new CarServiceImpl();
        carService.getCarByID(0L);

    }

}
