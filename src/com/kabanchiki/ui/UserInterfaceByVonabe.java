package com.kabanchiki.ui;

import java.util.Scanner;

public class UserInterfaceByVonabe {

    public UserInterfaceByVonabe() {
        Scanner scanner = new Scanner(System.in);

        // Главное меню
        MenuItem mainMenu = new MenuItem("Главное меню", false);

        // Подменю для "Добавить данные"
        MenuItem addDataMenu = new MenuItem("Добавить данные", () -> {
        }, false);
        addDataMenu.addSubItem(new MenuItem("Выберите тип данных:", true)); // Заголовок
        addDataMenu.addSubItem(new MenuItem("Car", () -> {
            System.out.println("Вы выбрали Car.");
        }));
        addDataMenu.addSubItem(new MenuItem("Book", () -> {
            System.out.println("Вы выбрали Book.");
        }));
        addDataMenu.addSubItem(new MenuItem("Root", () -> {
            System.out.println("Вы выбрали Root.");
        }));
        addDataMenu.addSubItem(new MenuItem("Все", () -> {
            System.out.println("Вы выбрали Все.");
        }));
        addDataMenu.addSubItem(new MenuItem("Выберите источник данных для массива:", true)); // Заголовок
        addDataMenu.addSubItem(new MenuItem("Файл", () -> {
            System.out.println("Данные будут загружены из файла.");
        }));
        addDataMenu.addSubItem(new MenuItem("Вручную", () -> {
            System.out.println("Введите данные вручную.");
        }));
        addDataMenu.addSubItem(new MenuItem("Рандом", () -> {
            System.out.println("Данные будут сгенерированы случайным образом.");
        }));
        addDataMenu.addSubItem(new MenuItem("Сохранить в файл?", true)); // Заголовок
        addDataMenu.addSubItem(new MenuItem("Да", () -> {
            System.out.println("Данные сохранены!");
        }));
        addDataMenu.addSubItem(new MenuItem("Нет", () -> {
            System.out.println("Данные не сохранены.");
        }));

//        // Подменю для "Сортировать данные"
//        MenuItem sortDataMenu = new MenuItem("Сортировать данные (стандартная сортировка)", () -> {
//        });
//        sortDataMenu.addSubItem(new MenuItem("Выберите класс для сортировки", true)); // Заголовок
//        sortDataMenu.addSubItem(new MenuItem("Машины", () -> {
//            System.out.println("Сортировка машин...");
//        }));
//        sortDataMenu.addSubItem(new MenuItem("Книги", () -> {
//            System.out.println("Сортировка книг...");
//        }));
//
//        // Добавляем подменю в главное меню
//        mainMenu.addSubItem(addDataMenu);
//        mainMenu.addSubItem(sortDataMenu);
//        mainMenu.addSubItem(new MenuItem("Выйти", () -> {
//            System.out.println("Выход из программы...");
//            System.exit(0);
//        }));

        // Запускаем главное меню
        mainMenu.displayMenu(scanner);

    }

}
