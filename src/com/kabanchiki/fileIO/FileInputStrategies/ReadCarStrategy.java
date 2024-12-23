package com.kabanchiki.fileIO.FileInputStrategies;

import com.kabanchiki.core.models.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCarStrategy implements InputStrategy {
    @Override
    public <T> List<T> readData(String data, int maxSize) {
        String regex = "Car\\[model='(.*?)', year=(\\d+), capacity=(\\d+)]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        List<T> cars = new ArrayList<>();
        while (matcher.find() && cars.size() < maxSize) {
            cars.add(((T) new Car.CarBuilder(matcher.group(1))
                    .setYear(Integer.parseInt(matcher.group(2)))
                    .setCapacity(Integer.parseInt(matcher.group(3)))
                    .build()));
        }

        return cars;
    }
}
