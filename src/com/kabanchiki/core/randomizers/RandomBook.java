package com.kabanchiki.core.randomizers;

import com.kabanchiki.core.models.Book;

import java.util.Random;

public class RandomBook {
    private String[] titles = {"java", "c++", "python", "javaScript", "c#", "html", "css", "golang", "how to be a good kabanchik"};
    private String[] authors = {"kabanchiki", "microsoft", "google", "oracle", "idk", "some smart guy"};

    public Book getRandomBook() {
        Random random = new Random();
        return new Book.BookBuilder(titles[random.nextInt(9)])
                .setAuthor(authors[random.nextInt(6)])
                .setPages(random.nextInt(200) + 150)
                .build();
    }
}
