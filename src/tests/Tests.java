package tests;

import com.kabanchiki.algorithms.searching.BinarySearch;
import com.kabanchiki.algorithms.sorting.SortStrategyManager;
import com.kabanchiki.algorithms.sorting.impl.CustomSortStrategy;
import com.kabanchiki.algorithms.sorting.impl.MergeSortStrategy;
import com.kabanchiki.core.comparators.BookComparator;
import com.kabanchiki.core.models.Book;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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

    private List<Book> getMockBook2() {
        return Arrays.asList(
                // Использование Builder для всех объектов Book
                new Book.BookBuilder("A").setAuthor("Author1").setPages(2).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(7).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(3).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(8).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(11).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(6).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(9).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(5).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(10).build(),
                new Book.BookBuilder("B").setAuthor("Author2").setPages(1).build(),
                new Book.BookBuilder("A").setAuthor("Author3").setPages(4).build()
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

    public void testSearch() {
        List<Book> books = getMockBook2();

        List<Book> sort = new MergeSortStrategy<>(BookComparator.TITLE).sort(books);

        // Компаратор для сравнения строки с названием книги
        int i = BinarySearch.binarySearch(sort, "A", Book::getTitle);
//        int index = Collections.binarySearch(sort, new Book.BookBuilder("").setPages(10).build(), BookComparator.PAGES);

        System.out.println("Sorted index: " + i);
        if (i >= 0) System.out.println(sort.get(i));
    }

    public void testSorting() {
        List<Book> books = getMockBook2();

        System.out.println("RAW DATA");
        books.forEach(System.out::println);

        SortStrategyManager<Book> sortHolder = new SortStrategyManager<>(new MergeSortStrategy<Book>());
        List<Book> bookSorted = sortHolder.sort(books);

        System.out.println("MERGE SORTED DATA");
        bookSorted.forEach(System.out::println);

        sortHolder.setSortStrategy(new CustomSortStrategy<>(BookComparator.PAGES, Book::getPages));
        List<Book> bookCustomSort = sortHolder.sort(books);

        System.out.println("CUSTOM SORTED DATA");
        bookCustomSort.forEach(System.out::println);
    }

    private void ext(Function<String, Integer> extractor) {
        Integer apply = extractor.apply("1");
        System.out.println(apply);
    }

    public void testAll() {
        testAllComparator();
        testSortingByAuthor();
        testSortingByPages();
        testSortingByTitle();

        testSorting();
        testSearch();
    }

    public static void main(String[] args) {
        new Tests().testAll();
    }
}
