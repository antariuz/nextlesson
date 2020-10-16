import com.fasterxml.jackson.databind.ObjectMapper;
import model.Car;

import java.io.IOException;
import java.util.List;

public class JSONCar {

    public String toJSON(Car car) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String toJSON(List<Car> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public Car toJavaObject(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car();
        try {
            car = objectMapper.readValue(json, Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return car;
    }

}
