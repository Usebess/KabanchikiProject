package com.kabanchiki.fileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileOutput {
    private final String FILES_PATH = "src/com/kabanchiki/fileIO/";

    // При создании экземпляра чистим файлы, оставшиеся с прошлого выполнения программы.
    public FileOutput() {
        try {
            Files.deleteIfExists(Paths.get(FILES_PATH + "Book.txt"));
            Files.deleteIfExists(Paths.get(FILES_PATH + "Car.txt"));
            Files.deleteIfExists(Paths.get(FILES_PATH + "Root.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запись элементов списка в файл.
     * @param <T>           Тип данных элементов списка.
     * @param dataToWrite   Список с объектами для записи.
     */
    public <T> void writeDataToFile(List<T> dataToWrite) {
        if(!dataToWrite.isEmpty()) {
            Path file = Paths.get(FILES_PATH + dataToWrite.get(0).getClass().getSimpleName() + ".txt");
            String elements = dataToWrite.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("\n")); // Записываем в строку данные объекта.

            // Если файл существует, дописываем данные в него. Иначе создаем новый и записываем.
            try {
                if (Files.exists(file)) {
                    Files.write(file, (elements + "\n").getBytes(), StandardOpenOption.APPEND);
                } else {
                    Files.write(file, (elements + "\n").getBytes(), StandardOpenOption.CREATE_NEW);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> void writeFoundDataToFile(T element) {
        Path file = Paths.get(FILES_PATH + "found" + element.getClass().getSimpleName() + "s.txt");

        // Если файл существует, дописываем данные в него. Иначе создаем новый и записываем.
        try {
            if (Files.exists(file)) {
                Files.write(file, (element + "\n").getBytes(), StandardOpenOption.APPEND);
            } else {
                Files.write(file, (element + "\n").getBytes(), StandardOpenOption.CREATE_NEW);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
