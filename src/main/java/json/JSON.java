package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Car;
import model.Person;

import java.io.IOException;
import java.util.List;

public class JSON {

    public String personToJSON(Person person) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String personListToJSON(List<Person> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public Person jsonToPerson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        try {
            person = objectMapper.readValue(json, model.Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    public String carToJSON(Car car) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String carListToJSON(List<Car> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public Car jsonToCar(String json) {
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