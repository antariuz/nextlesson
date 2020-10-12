import model.Person;

public interface PersonFactory {
    Person createPersonDTO();

    Person createPersonVO();
}
