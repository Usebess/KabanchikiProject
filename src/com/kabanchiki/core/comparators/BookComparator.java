package com.kabanchiki.core.comparators;

import com.kabanchiki.core.models.Book;

import java.util.Comparator;

public final class BookComparator {
    private BookComparator() {}

    /**
     * Компаратор, сортирующий книги по названию, затем по автору и числу страниц.
     */
    public static final Comparator<Book> ALL = Comparator.comparing(Book::getTitle)
            .thenComparing(Book::getAuthor)
            .thenComparingInt(Book::getPages);
    public static final Comparator<Book> TITLE = Comparator.comparing(Book::getTitle);
    public static final Comparator<Book> AUTHOR = Comparator.comparing(Book::getAuthor);
    public static final Comparator<Book> PAGES = Comparator.comparingInt(Book::getPages);
}
