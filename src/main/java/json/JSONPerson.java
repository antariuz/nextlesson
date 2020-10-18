package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;

import java.io.IOException;
import java.util.List;

public class JSONPerson {

    public String toJSON(Person person) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String toJSON(List<Person> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public Person toJavaObject(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        try {
            person = objectMapper.readValue(json, model.Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

}
