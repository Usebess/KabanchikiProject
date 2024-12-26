package com.kabanchiki.fileIO.fileInputStrategies;

import com.kabanchiki.core.models.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCarStrategy implements InputStrategy {
    /**
     * Выделить информацию об объектах в строке и записать в список.
     * @param <T>       Тип данных списка.
     * @param data      Строка с информацией.
     * @param maxSize   Максимальный размер возвращаемой строки.
     * @return Список пустой или заполненный данными из строки.
     */
    @Override
    public <T> List<T> readData(String data, int maxSize) {
        String regex = "Car\\[model='(.*?)', year=(\\d+), capacity=(\\d+)]";

        // Применяем регулярное выражение к строке.
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        // Заполняем полученными данными список.
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