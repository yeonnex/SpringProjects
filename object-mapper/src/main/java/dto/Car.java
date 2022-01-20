package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    private String name;

    @JsonProperty("car_number")
    private String car_number;

    @JsonProperty("TYPE")
    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    private String TYPE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", car_number='" + car_number + '\'' +
                ", TYPE='" + TYPE + '\'' +
                '}';
    }
}
