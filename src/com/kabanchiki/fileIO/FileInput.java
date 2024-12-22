package com.kabanchiki.fileIO;

import com.kabanchiki.core.models.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInput {

    public <T> List<T> readDataFromFile(Class<T> type) {
        String data = "";
        Path file = Paths.get("src/com/kabanchiki/fileIO/" + type.getSimpleName() + ".txt");

        try {
            if (Files.exists(file)) data = Files.readString(file);
            else {
                return new ArrayList<>();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (type == Book.class) return readBooks(data);
        else if (type == Car.class) return readCars(data);
        else if (type == Root.class) return readRoots(data);
        else return new ArrayList<>();
    }

    private <T> List<T> readBooks(String data) {
        String regex = "Book\\[title='(.*?)', author='(.*?)', pages=(\\d+)]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        List<T> books = new ArrayList<>();
        while (matcher.find()) {
            books.add((T) new Book.BookBuilder(matcher.group(1))
                    .setAuthor(matcher.group(2))
                    .setPages(Integer.parseInt(matcher.group(3)))
                    .build());
        }

        return books;
    }

    private <T> List<T> readCars(String data) {
        String regex = "Car\\[model='(.*?)', year=(\\d+), capacity=(\\d+)]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        List<T> cars = new ArrayList<>();
        while (matcher.find()) {
            cars.add(((T) new Car.CarBuilder(matcher.group(1))
                    .setYear(Integer.parseInt(matcher.group(2)))
                    .setCapacity(Integer.parseInt(matcher.group(3)))
                    .build()));
        }

        return cars;
    }

    private <T> List<T> readRoots(String data) {
        String regex = "Root\\[type='(.*?)', weight=(\\d+), color='(.*?)']";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        List<T> roots = new ArrayList<>();
        while (matcher.find()) {
            roots.add((T) new Root.RootBuilder(matcher.group(1))
                    .setWeight(Integer.parseInt(matcher.group(2)))
                    .setColor(matcher.group(3))
                    .build());
        }

        return roots;
    }
}
