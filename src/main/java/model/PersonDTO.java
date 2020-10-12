package model;

public class PersonDTO {

    public Person createPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getID());
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setAge(personDTO.getAge());
        return person;
    }

}
