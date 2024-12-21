package com.kabanchiki.core;

public class Car {
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
