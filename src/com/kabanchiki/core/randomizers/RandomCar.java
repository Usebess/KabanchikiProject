package com.kabanchiki.core.randomizers;

import com.kabanchiki.core.models.Car;

import java.util.Random;

public class RandomCar {
    private String[] models = {"porsche 911", "ваз-2107", "dodge charger", "toyota mark x", "subaru impreza 555", "volvo s80"};

    public Car getRandomCar() {
        Random random = new Random();
        return new Car.CarBuilder(models[random.nextInt(6)])
                .setCapacity(random.nextInt(300))
                .setYear(random.nextInt(34) + 1990)
                .build();
    }
}
