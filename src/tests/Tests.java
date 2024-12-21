package tests;

import com.kabanchiki.core.comparators.BookComparator;
import com.kabanchiki.core.models.Book;

import java.util.Arrays;
import java.util.List;

public class Tests {

    private List<Book> getMockBook() {
        return Arrays.asList(
                // Использование Builder для всех объектов Book
                new Book.BookBuilder("B").setAuthor("Author2").setPages(150).build(),
                new Book.BookBuilder("A").setAuthor("Author1").setPages(200).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(99).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(100).build()
        );
    }

    private void testSortingByTitle() {
        // Пример теста для сортировки по заголовку
        List<Book> books = getMockBook();
        books.sort(BookComparator.TITLE);

        assert books.get(0).getTitle().equals("A");
        assert books.get(1).getTitle().equals("A");
        assert books.get(2).getTitle().equals("A");
        assert books.get(3).getTitle().equals("B");
    }

    private void testSortingByAuthor() {
        // Пример теста для сортировки по автору
        List<Book> books = getMockBook();
        books.sort(BookComparator.AUTHOR);

        assert books.get(0).getAuthor().equals("Author1");
        assert books.get(1).getAuthor().equals("Author2");
        assert books.get(2).getAuthor().equals("Author3");
        assert books.get(3).getAuthor().equals("Author3");
    }

    private void testSortingByPages() {
        // Пример теста для сортировки по количеству страниц
        List<Book> books = getMockBook();
        books.sort(BookComparator.PAGES);

        assert books.get(0).getPages() == 99;
        assert books.get(1).getPages() == 100;
        assert books.get(2).getPages() == 150;
        assert books.get(3).getPages() == 200;
    }

    public void testAllComparator() {
        List<Book> books = getMockBook();
        // Сортировка с использованием BookComparator.ALL
        books.sort(BookComparator.ALL);

        // Проверьте порядок после сортировки
        assert List.of(
                new Book.BookBuilder("A").setAuthor("Author1").setPages(200).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(99).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(100).build(),
                new Book.BookBuilder("B").setAuthor("Author2").setPages(150).build()).equals(books);
    }

    public void testAll() {
        testAllComparator();
        testSortingByAuthor();
        testSortingByPages();
        testSortingByTitle();
    }

    public static void main(String[] args) {
        new Tests().testAll();
    }
}
