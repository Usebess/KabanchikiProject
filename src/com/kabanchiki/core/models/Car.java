package com.kabanchiki.core.models;

import com.kabanchiki.core.comparators.CarComparator;

import java.util.Objects;
import java.util.StringJoiner;

public class Car implements Comparable<Car> {
    private String model;
    private int year;
    private int capacity;

    private Car(CarBuilder carBuilder) {
        this.model = carBuilder.model;
        this.year = carBuilder.year;
        this.capacity = carBuilder.capacity;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public int compareTo(Car o) {
        return CarComparator.ALL.compare(this, o);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Car car)) return false;
        return year == car.year && capacity == car.capacity && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, year, capacity);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("model='" + model + "'")
                .add("year=" + year)
                .add("capacity=" + capacity)
                .toString();
    }

    public static class CarBuilder {
        private String model;
        private int year;
        private int capacity;

        public CarBuilder(String model) {
            this.model = model;
        }

        public CarBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public CarBuilder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
