package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


public class JSON <E> {

    public String toJSON(E e) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(e);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return json;
    }

}