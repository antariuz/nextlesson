package factory;

import model.Person;
import model.PersonDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum PersonFactory {
    INSTANCE;

    private final Logger LOGGER = LogManager.getLogger(PersonFactory.class.getName());

    PersonFactory() {
    }

    public static PersonFactory getInstance() {
        return INSTANCE;
    }

    public PersonDTO createPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setID(person.getId());
        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        personDTO.setAge(person.getAge());
        return personDTO;
    }

    public Person createPersonVO(ResultSet resultSet) {
        Person person = new Person();
        try {
            while (resultSet.next()) {
                person.setId(resultSet.getLong("person_id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return person;
    }

    public Person createPersonVO(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getID());
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setAge(personDTO.getAge());
        return person;
    }

    public List<PersonDTO> createDTOList(List<Person> listVO) {
        if (listVO == null) return new ArrayList<>();
        List<PersonDTO> listDTO = new ArrayList<>();
        for (Person person : listVO) {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setID(person.getId());
            personDTO.setName(person.getName());
            personDTO.setSurname(person.getSurname());
            personDTO.setAge(person.getAge());
            listDTO.add(personDTO);
        }
        return listDTO;
    }

    public List<Person> createVOList(List<PersonDTO> listDTO) {
        if (listDTO == null) return null;
        List<Person> listVO = new ArrayList<>();
        for (PersonDTO personDTO : listDTO) {
            Person person = new Person();
            person.setId(personDTO.getID());
            person.setName(personDTO.getName());
            person.setSurname(personDTO.getSurname());
            person.setAge(personDTO.getAge());
            listVO.add(person);
        }
        return listVO;
    }

    public List<Person> createVOList(ResultSet resultSet) {
        List<Person> listVO = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("person_id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
                listVO.add(person);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return listVO;
    }

}
