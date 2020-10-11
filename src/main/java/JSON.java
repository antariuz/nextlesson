import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;

import java.io.File;
import java.io.IOException;

public class JSON {

    private final static String baseFile = "person.json";

    public static void toJSON(Person person) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), person);
        System.out.println("json created!");
    }

    public static Person toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), model.Person.class);
    }

}
