package com.kabanchiki.fileIO.fileInputStrategies;

import com.kabanchiki.core.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadBookStrategy implements InputStrategy {
    /**
     * Выделить информацию об объектах в строке и записать в список.
     * @param <T>       Тип данных списка.
     * @param data      Строка с информацией.
     * @param maxSize   Максимальный размер возвращаемой строки.
     * @return Список пустой или заполненный данными из строки.
     */
    @Override
    public <T> List<T> readData(String data, int maxSize) {
        String regex = "Book\\[title='(.*?)', author='(.*?)', pages=(\\d+)]";

        // Применяем регулярное выражение к строке.
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        // Заполняем полученными данными список.
        List<T> books = new ArrayList<>();
        while (matcher.find() && books.size() < maxSize) {
            books.add((T) new Book.BookBuilder(matcher.group(1))
                    .setAuthor(matcher.group(2))
                    .setPages(Integer.parseInt(matcher.group(3)))
                    .build());
        }
        return books;
    }
}
