package com.kabanchiki.core.comparators;

import com.kabanchiki.core.models.Car;

import java.util.Comparator;

public final class CarComparator {
    private CarComparator() {}

    /**
     * Компаратор, сортирующий Машины по модели, затем по году и мощности.
     */
    public static final Comparator<Car> ALL = Comparator.comparing(Car::getModel)
            .thenComparingInt(Car::getYear)
            .thenComparingInt(Car::getCapacity);
    public static final Comparator<Car> CAPACITY = Comparator.comparingInt(Car::getCapacity);
    public static final Comparator<Car> YEARS = Comparator.comparingInt(Car::getYear);
    public static final Comparator<Car> MODEL = Comparator.comparing(Car::getModel);
}
