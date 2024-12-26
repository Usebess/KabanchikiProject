package com.kabanchiki.ui;

import com.kabanchiki.algorithms.searching.BinarySearch;
import com.kabanchiki.algorithms.sorting.SortManager;
import com.kabanchiki.algorithms.sorting.impl.CustomSortStrategy;
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
import java.util.ArrayList;
import java.util.List;

public class UserInterface extends JFrame {

    private List<Car> cars = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Root> roots = new ArrayList<>();

    private FileInput fileInput = new FileInput();

    private final SortManager sortingManager = new SortManager<>();

    private final List<String> styles = List.of(
            "javax.swing.plaf.metal.MetalLookAndFeel",               // Metal (CrossPlatformLookAndFeel)
            "javax.swing.plaf.nimbus.NimbusLookAndFeel",             // Nimbus
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel",    // Windows
            "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel", // Windows Classic
            "com.sun.java.swing.plaf.gtk.GTKLookAndFeel",            // GTK+
            "com.sun.java.swing.plaf.motif.MotifLookAndFeel"         // Motif
    );

    public UserInterface() {
        try {
            // Устанавливаем Nimbus LAF
            UIManager.setLookAndFeel(styles.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Главное меню");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        JButton dataSourceButton = new JButton("1. Выбор источника данных для массива");
        JButton sortingButton = new JButton("2. Сортировка массива и бинарный поиск");
        JButton carCustomSortingButton = new JButton("3. Кастомная сортировка Car по числовому полю");
        JButton rootCustomSortingButton = new JButton("4. Кастомная сортировка Root по числовому полю");
        JButton bookCustomSortingButton = new JButton("5. Кастомная сортировка Book по числовому полю");
        JButton exitButton = new JButton("6. Выход");

        //Сами кнопки на сортировку - ввод наших функций
        dataSourceButton.addActionListener(s -> chooseDataSource());

        sortingButton.addActionListener(s -> sortAndSearch());

        carCustomSortingButton.addActionListener(s -> {
            String parameterInput = chooseDialog("Введите параметр по которому будет производиться сортировка (год или мощность):", "", new String[]{"Год", "Мощность"});
//            String parameterInput = JOptionPane.showInputDialog("Введите параметр по которому будет производиться сортировка (год или мощность):");
            if (parameterInput != null)
                switch (parameterInput.toLowerCase()) {
                    case "год" -> {
                        sortingManager.setSortStrategy(new CustomSortStrategy<>(CarComparator.YEARS, Car::getYear));
                        cars = sortingManager.sort(cars);
                        System.out.println("\nОтсортированный список машин по " + parameterInput + ":");
                        cars.forEach(System.out::println);
                    }

                    case "мощность" -> {
                        sortingManager.setSortStrategy(new CustomSortStrategy<>(CarComparator.CAPACITY, Car::getCapacity));
                        cars = sortingManager.sort(cars);
                        System.out.println("\nОтсортированный список машин по " + parameterInput + ":");
                        cars.forEach(System.out::println);
                    }

                    default -> System.out.println("Введено неверное значение параметра");
                }
        });

        rootCustomSortingButton.addActionListener(s -> {
            sortingManager.setSortStrategy(new CustomSortStrategy<>(RootComparator.WEIGHT, Root::getWeight));
            roots = sortingManager.sort(roots);
            System.out.println("\nОтсортированный список корнеплодов по весу:");
            roots.forEach(System.out::println);
        });

        bookCustomSortingButton.addActionListener(s -> {
            sortingManager.setSortStrategy(new CustomSortStrategy<>(BookComparator.PAGES, Book::getPages));
            books = sortingManager.sort(books);

            System.out.println("\nОтсортированный список книг по страницам:");
            books.forEach(System.out::println);
        });

        exitButton.addActionListener(s -> {
            FileOutput fileOutput = new FileOutput();
            fileOutput.writeDataToFile(cars);
            fileOutput.writeDataToFile(books);
            fileOutput.writeDataToFile(roots);
            System.exit(0);
        });

        add(dataSourceButton);

        add(sortingButton);

        add(carCustomSortingButton);

        add(rootCustomSortingButton);

        add(bookCustomSortingButton);

        add(exitButton);

        setVisible(true);
    }

    private String chooseDialog(String title, String content, String[] choose) {
        return (String) JOptionPane.showInputDialog(null, content,
                title, JOptionPane.QUESTION_MESSAGE, null, choose, choose[0]);
    }

    private void chooseDataSource() {
        String[] options = {"Файлы", "Рандом", "Ручной ввод"};
        String choice = chooseDialog("Выбор источника данных", "", options);

        if (choice != null) {
            String lengthInput = JOptionPane.showInputDialog("Введите длину массива:");
            if (lengthInput != null) {
                try {
                    int length = Integer.parseInt(lengthInput);
                    fillArray(choice, length);
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат ввода");
                }
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
                try {
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
                } catch (Exception e) {
                    System.out.println("Invalid input data");
                }
            }
        }
    }

    private void sortAndSearch() {
        String parameterTypeInput;
        int resultIndex;

        sortingManager.setSortStrategy(new MergeSortStrategy<>());
        List<Book> booksSorted = sortingManager.sort(books);
        List<Car> carsSorted = sortingManager.sort(cars);
        List<Root> rootsSorted = sortingManager.sort(roots);

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

        String classTypeInput = chooseDialog("Input", "Введите класс по которому будет производиться бинарный поиск:", new String[]{"CAR", "BOOK", "ROOT"});
//        String classTypeInput = JOptionPane.showInputDialog("Введите класс по которому будет производиться бинарный поиск:");
        if (classTypeInput != null)
            try {
                switch (classTypeInput.toLowerCase()) {
                    case "car" -> {
                        String target = JOptionPane.showInputDialog("Введите искомое значение:");
                        resultIndex = BinarySearch.binarySearch(carsSorted, target.toLowerCase(), Car::getModel);

                        if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                        else {
                            FileOutput fileOutput = new FileOutput();
                            fileOutput.writeFoundDataToFile(carsSorted.get(resultIndex));
                            System.out.println("Индекс искомого значения: " + resultIndex);
                        }
                    }
                    case "book" -> {
                        String target = JOptionPane.showInputDialog("Введите искомое значение:");
                        resultIndex = BinarySearch.binarySearch(booksSorted, target.toLowerCase(), Book::getTitle);

                        if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                        else {
                            FileOutput fileOutput = new FileOutput();
                            fileOutput.writeFoundDataToFile(booksSorted.get(resultIndex));
                            System.out.println("Индекс искомого значения: " + resultIndex);
                        }
                    }
                    case "root" -> {
                        String target = JOptionPane.showInputDialog("Введите искомое значение:");
                        resultIndex = BinarySearch.binarySearch(rootsSorted, target.toLowerCase(), Root::getType);
                        if (resultIndex < 0) System.out.println("Искомое значение отсутствует");
                        else {
                            FileOutput fileOutput = new FileOutput();
                            fileOutput.writeFoundDataToFile(rootsSorted.get(resultIndex));
                            System.out.println("Индекс искомого значения: " + resultIndex);
                        }
                    }
                    default -> System.out.println("Введено неверное значение");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}
