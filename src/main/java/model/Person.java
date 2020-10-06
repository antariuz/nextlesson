package model;

public class Person {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;

    public static class Builder {

        private Person newPerson;

        public Builder() {
            newPerson = new Person();
        }

        public Builder withID(Integer id){
            newPerson.id = id;
            return this;
        }

        public Builder withName(String name){
            newPerson.name = name;
            return this;
        }

        public Builder withSurname(String surname){
            newPerson.surname = surname;
            return this;
        }

        public Builder withAge(Integer age){
            newPerson.age = age;
            return this;
        }

        public Person build(){
            return newPerson;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return id + ","
                + name + ","
                + surname + ","
                + age;
    }
}
