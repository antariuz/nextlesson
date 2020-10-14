import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;

import java.io.IOException;

public class JSON {

    public void toJSON(Person person) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
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
