import model.Person;
import model.PersonDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonFactory {

    //SINGLETON IMPLEMENTATION
    enum SingletonHolder {
        INSTANCE;

        public SingletonHolder getInstance() {
            return INSTANCE;
        }
    }

    private PersonFactory() {
    }

    public PersonDTO createPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setID(person.getId());
        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        personDTO.setAge(person.getAge());
        return personDTO;
    }

    public Person createPersonVO(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getID());
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setAge(personDTO.getAge());
        return person;
    }

    public List<PersonDTO> createDTOList(List<Person> listDTO) {
        if (listDTO == null) return new ArrayList<>();
        return listDTO.stream().map(this::createPersonDTO).collect(Collectors.toList());
    }

    public List<Person> createVOList(List<PersonDTO> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream().map(this::createPersonVO).collect(Collectors.toList());
    }

}
