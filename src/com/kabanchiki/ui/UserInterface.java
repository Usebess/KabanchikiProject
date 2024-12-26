package com.kabanchiki.ui;

import com.kabanchiki.algorithms.searching.BinarySearch;
import com.kabanchiki.algorithms.sorting.SortStrategyManager;
import com.kabanchiki.algorithms.sorting.impl.MergeSortStrategy;
import com.kabanchiki.core.comparators.BookComparator;
import com.kabanchiki.core.comparators.CarComparator;
import com.kabanchiki.core.comparators.RootComparator;
import com.kabanchiki.core.models.Book;
import com.kabanchiki.core.models.Car;
import com.kabanchiki.core.models.Root;
import com.kabanchiki.core.randomizers.RandomBook;
import com.kabanchiki.core.randomizers.RandomCar;
import com.kabanchiki.core.randomizers.RandomRoot;
import com.kabanchiki.fileIO.FileInput;
import com.kabanchiki.fileIO.FileOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserInterface extends JFrame {

    private List<Car> cars = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Root> roots = new ArrayList<>();

    private FileInput fileInput = new FileInput();

    public UserInterface() {
        setTitle("Главное меню");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        JButton dataSourceButton = new JButton("1. Выбор источника данных для массива");
        JButton sortingButton = new JButton("2. Сортировка массива и бинарный поиск");
        JButton carSortingButton = new JButton("3. Кастомная сортировка Car по числовому полю");
        JButton rootSortingButton = new JButton("4. Кастомная сортировка Root по числовому полю");
        JButton bookSortingButton = new JButton("5. Кастомная сортировка Book по числовому полю");
        JButton exitButton = new JButton("6. Выход");

        //Сами кнопки на сортировку - ввод наших функций
        dataSourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseDataSource();
            }
        });

        sortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String parameterTypeInput;
                int resultIndex = -1;

                SortStrategyManager<Book> bookSortStrategyManager = new SortStrategyManager<>(new MergeSortStrategy<>(BookComparator.ALL));
                SortStrategyManager<Car> carSortStrategyManager = new SortStrategyManager<>(new MergeSortStrategy<>(CarComparator.ALL));
                SortStrategyManager<Root> rootSortStrategyManager = new SortStrategyManager<>(new MergeSortStrategy<>(RootComparator.ALL));

                List<Book> booksSorted = bookSortStrategyManager.sort(books);
                List<Car> carsSorted = carSortStrategyManager.sort(cars);
                List<Root> rootsSorted = rootSortStrategyManager.sort(roots);

                System.out.println("\nОтсортированные массивы:\n\nКниги:");

                if (!booksSorted.isEmpty()) {
                    booksSorted.forEach(System.out::println);
                }

                System.out.println("\nМашины:");

                if (!carsSorted.isEmpty()) {
                    carsSorted.forEach(System.out::println);
                }

                System.out.println("\nКорнеплоды:");

                if (!rootsSorted.isEmpty()) {
                    rootsSorted.forEach(System.out::println);
                }

                String classTypeInput = JOptionPane.showInputDialog("Введите класс по которому будет производиться бинарный поиск:");
                switch (classTypeInput.toLowerCase()) {

                    case "car" -> {

                        parameterTypeInput = JOptionPane.showInputDialog("Введите поле по которому будет происходить поиск (модель, год. мощность):");
                        switch (parameterTypeInput.toLowerCase()) {

                            case "модель" -> {
                                String target = JOptionPane.showInputDialog("Введите искомое значение:");
                                resultIndex = BinarySearch.binarySearch(carsSorted, target.toLowerCase(), Car::getModel);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            case "мощность" -> {
                                int target = Integer.parseInt(JOptionPane.showInputDialog("Введите искомое значение:"));
                                resultIndex = BinarySearch.binarySearch(carsSorted, target, Car::getCapacity);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            case "год" -> {
                                int target = Integer.parseInt(JOptionPane.showInputDialog("Введите искомое значение:"));
                                resultIndex = BinarySearch.binarySearch(carsSorted, target, Car::getYear);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            default -> {
                                System.out.println("Введено неверное значение");
                            }
                        }
                    }

                    case "book" -> {

                        parameterTypeInput = JOptionPane.showInputDialog("Введите поле по которому будет происходить поиск (название, автор, страницы):");
                        switch (parameterTypeInput.toLowerCase()) {

                            case "название" -> {
                                String target = JOptionPane.showInputDialog("Введите искомое значение:");
                                resultIndex = BinarySearch.binarySearch(booksSorted, target.toLowerCase(), Book::getTitle);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            case "мощность" -> {
                                String target = JOptionPane.showInputDialog("Введите искомое значение:");
                                resultIndex = BinarySearch.binarySearch(booksSorted, target, Book::getAuthor);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            case "страницы" -> {
                                int target = Integer.parseInt(JOptionPane.showInputDialog("Введите искомое значение:"));
                                resultIndex = BinarySearch.binarySearch(booksSorted, target, Book::getPages);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            default -> {
                                System.out.println("Введено неверное значение");
                            }
                        }
                    }

                    case "root" -> {

                        parameterTypeInput = JOptionPane.showInputDialog("Введите поле по которому будет происходить поиск (тип, цвет, вес):");
                        switch (parameterTypeInput.toLowerCase()) {

                            case "тип" -> {
                                String target = JOptionPane.showInputDialog("Введите искомое значение:");
                                resultIndex = BinarySearch.binarySearch(rootsSorted, target.toLowerCase(), Root::getType);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            case "цвет" -> {
                                String target = JOptionPane.showInputDialog("Введите искомое значение:");
                                resultIndex = BinarySearch.binarySearch(rootsSorted, target, Root::getColor);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            case "вес" -> {
                                int target = Integer.parseInt(JOptionPane.showInputDialog("Введите искомое значение:"));
                                resultIndex = BinarySearch.binarySearch(rootsSorted, target, Root::getWeight);

                                if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                                else System.out.println("Индекс искомого значения: " + resultIndex);
                            }

                            default -> {
                                System.out.println("Введено неверное значение");
                            }
                        }
                    }

                    default -> System.out.println("Введено неверное значение");
                }
            }
        });

        carSortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String parameterInput = JOptionPane.showInputDialog("Введите параметр по которому будет производиться сортировка (год или мощность):");
                switch (parameterInput.toLowerCase()) {

                    case "год" -> {
                        cars.sort(CarComparator.YEARS);
                        System.out.println("\nОтсортированный список машин по " + parameterInput + ":");
                        cars.forEach(System.out::println);
                    }

                    case "мощность" -> {
                        cars.sort(CarComparator.CAPACITY);
                        System.out.println("\nОтсортированный список машин по " + parameterInput + ":");
                        cars.forEach(System.out::println);
                    }

                    default -> {
                        System.out.println("Введено неверное значение параметра");
                    }
                }
            }
        });

        rootSortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                roots.sort(RootComparator.WEIGHT);
                System.out.println("\nОтсортированный список корнеплодов по весу:");
                roots.forEach(System.out::println);
            }
        });

        bookSortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                books.sort(BookComparator.PAGES);
                System.out.println("\nОтсортированный список книг по страницам:");
                books.forEach(System.out::println);

            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                FileOutput fileOutput = new FileOutput();
                fileOutput.writeDataToFile(cars);
                fileOutput.writeDataToFile(books);
                fileOutput.writeDataToFile(roots);

                System.exit(0);
            }
        });

        add(dataSourceButton);

        add(sortingButton);

        add(carSortingButton);

        add(rootSortingButton);

        add(bookSortingButton);

        add(exitButton);

        setVisible(true);
    }

    private void chooseDataSource() {
        String[] options = {"Файлы", "Рандом", "Ручной ввод"};
        String choice = (String) JOptionPane.showInputDialog(null, "Выберите источник:",
                "Выбор источника данных", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != null) {
            String lengthInput = JOptionPane.showInputDialog("Введите длину массива:");
            if (lengthInput != null) {
                int length = Integer.parseInt(lengthInput);
                fillArray(choice, length);
            }
        }
        System.out.println("Неотсортированные массивы:\n\nКниги:");
        books.forEach(System.out::println);
        System.out.println("\nМашины:");
        cars.forEach(System.out::println);
        System.out.println("\nКорнеплоды");
        roots.forEach(System.out::println);
    }

    private void fillArray(String source, int length) {

        cars.clear();
        books.clear();
        roots.clear();

        switch (source) {
            case "Файлы" -> {
                cars = fileInput.readDataFromFile(Car.class, length);
                books = fileInput.readDataFromFile(Book.class, length);
                roots = fileInput.readDataFromFile(Root.class, length);
            }
            case "Рандом" -> {
                for (int i = 0; i < length; i++) {
                    cars.add(new RandomCar().getRandomCar());
                    books.add(new RandomBook().getRandomBook());
                    roots.add(new RandomRoot().getRandomRoot());
                }
            }
            case "Ручной ввод" -> {
                for (int i = 0; i < length; i++) {

                    String carModelInput = JOptionPane.showInputDialog("Введите модель автомобиля:").toLowerCase();
                    String carCapacityInput = JOptionPane.showInputDialog("Введите мощность автомобиля:");
                    String carYearInput = JOptionPane.showInputDialog("Введите год производства автомобиля:");

                    String bookTitleInput = JOptionPane.showInputDialog("Введите название книги:").toLowerCase();
                    String bookAuthorInput = JOptionPane.showInputDialog("Введите автора книги:").toLowerCase();
                    String bookPagesInput = JOptionPane.showInputDialog("Введите количество страниц в книге:");

                    String rootTypeInput = JOptionPane.showInputDialog("Введите вид корнеплода").toLowerCase();
                    String rootColorInput = JOptionPane.showInputDialog("Введите цвет корнеплода:").toLowerCase();
                    String rootWeightInput = JOptionPane.showInputDialog("Введите вес корнеплода:");

                    cars.add(new Car.CarBuilder(carModelInput)
                            .setCapacity(carCapacityInput.isEmpty() ? 0 : Integer.parseInt(carCapacityInput))
                            .setYear(carYearInput.isEmpty() ? 0 : Integer.parseInt(carYearInput))
                            .build());

                    books.add(new Book.BookBuilder(bookTitleInput)
                            .setAuthor(bookAuthorInput)
                            .setPages(bookPagesInput.isEmpty() ? 0 : Integer.parseInt(bookPagesInput))
                            .build());

                    roots.add(new Root.RootBuilder(rootTypeInput)
                            .setColor(rootColorInput)
                            .setWeight(rootWeightInput.isEmpty() ? 0 : Integer.parseInt(rootWeightInput))
                            .build());
                }
            }
        }
    }
}