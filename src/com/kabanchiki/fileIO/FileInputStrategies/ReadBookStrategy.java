package com.kabanchiki.fileIO.FileInputStrategies;

import com.kabanchiki.core.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadBookStrategy implements InputStrategy {
    @Override
    public <T> List<T> readData(String data, int maxSize) {
        String regex = "Book\\[title='(.*?)', author='(.*?)', pages=(\\d+)]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

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
