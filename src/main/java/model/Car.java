package model;

public class Car {

    private Long id;
    private String model;
    private Integer manufacturedYear;

    public static class Builder {

        private final Car newCar;

        public Builder() {
            newCar = new Car();
        }

        public Builder withID(Long id) {
            newCar.id = id;
            return this;
        }

        public Builder withModel(String model) {
            newCar.model = model;
            return this;
        }

        public Builder withManufacturedYear(Integer manufacturedYear) {
            newCar.manufacturedYear = manufacturedYear;
            return this;
        }

        public Car build() {
            return newCar;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getManufacturedYear() {
        return manufacturedYear;
    }

    public void setManufacturedYear(Integer manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
    }

}
