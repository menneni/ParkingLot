package model.entity;

import model.interfaces.Vehicle;

/**
 * Created by thej on 3/10/16.
 */
public class Car implements Vehicle {

    String color;
    String regNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return regNo.equals(car.regNo);

    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }

    public Car(String regNo, String color) {
        this.color = color;
        this.regNo = regNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
