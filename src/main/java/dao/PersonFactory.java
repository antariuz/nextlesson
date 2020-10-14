package dao;

import model.Person;
import model.PersonDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum PersonFactory {

    //SINGLETON IMPLEMENTATION
    INSTANCE;

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
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
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

    public List<PersonDTO> createDTOList(List<Person> listDTO) {
        if (listDTO == null) return new ArrayList<>();
        return listDTO.stream().map(this::createPersonDTO).collect(Collectors.toList());
    }

    public List<Person> createVOList(List<PersonDTO> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream().map(this::createPersonVO).collect(Collectors.toList());
    }

}
