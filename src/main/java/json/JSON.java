package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class JSON <E> {

    private final Logger LOGGER = LogManager.getLogger(JSON.class.getName());

    public String toJSON(List<E> e) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(e);
        } catch (JsonProcessingException exception) {
            LOGGER.error(exception);
        }
        return json;
    }

}
