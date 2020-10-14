package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    @JsonProperty("Number")
    private Long id;
    @JsonProperty("Number")
    private String name;
    @JsonProperty("Number")
    private String surname;
    @JsonProperty("Number")
    private Integer age;

    public static class Builder {

        private final Person newPerson;

        public Builder() {
            newPerson = new Person();
        }

        public Builder withID(Long id) {
            newPerson.id = id;
            return this;
        }

        public Builder withName(String name) {
            newPerson.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            newPerson.surname = surname;
            return this;
        }

        public Builder withAge(Integer age) {
            newPerson.age = age;
            return this;
        }

        public Person build() {
            return newPerson;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{ID: " + id + ", Name: " + name + ", Surname: " + surname + ", Age: " + age + "}";
    }
}
