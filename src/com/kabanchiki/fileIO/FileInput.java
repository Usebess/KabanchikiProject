package com.kabanchiki.fileIO;

import com.kabanchiki.core.models.*;
import com.kabanchiki.fileIO.fileInputStrategies.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInput {

    /**
     * Считывание информации с файлов ClassName.txt
     * @param <T>       Принимает любые классы.
     * @param type      Хранит в себе тип класса.
     * @param maxSize   Максимальный размер возвращаемого списка.
     * @return Список объектов, созданных в соответствии с данными в файле, или пустой список.
     */
    public <T> List<T> readDataFromFile(Class<T> type, int maxSize) {
        String data = "";
        Path file = Paths.get("src/com/kabanchiki/fileIO/" + type.getSimpleName() + ".txt");

        // Если файл существует, записываем с него информацию. Иначе возвращаем пустой список.
        try {
            if (Files.exists(file)) data = Files.readString(file);
            else {
                return new ArrayList<>();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Опираясь на полученный класс выбираем стратегию считывания. Если подходящей нет - возвращаем пустой список.
        InputStrategy inputStrategy;
        if (type == Book.class) inputStrategy = new ReadBookStrategy();
        else if (type == Car.class) inputStrategy = new ReadCarStrategy();
        else if (type == Root.class) inputStrategy = new ReadRootStrategy();
        else return new ArrayList<>();

        // Вызываем метод для считывания данных и возвращаем результат выполнения.
        return inputStrategy.readData(data, maxSize);
    }
}
