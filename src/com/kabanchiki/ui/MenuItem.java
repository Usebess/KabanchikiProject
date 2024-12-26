package com.kabanchiki.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuItem {
    private String title;                  // Название пункта
    private Runnable action;              // Действие при выборе
    private List<MenuItem> subItems;      // Подменю
    private boolean isTitle;              // Определяет, является ли элемент заголовком

    public MenuItem(String title, Runnable action, boolean isTitle) {
        this.title = title;
        this.action = action;
        this.isTitle = isTitle;
        this.subItems = new ArrayList<>();
    }

    public MenuItem(String title, Runnable action) {
        this(title, action, false);
    }

    public MenuItem(String title, boolean isTitle) {
        this(title, null, isTitle);
    }

    public void addSubItem(MenuItem subItem) {
        subItems.add(subItem);
    }

    public void execute(Scanner scanner) {
        if (isTitle) {
            System.out.println("Нельзя выбрать заголовок!");
            return;
        }
        if (action != null) {
            action.run();  // Выполняем действие
        }
        if (!subItems.isEmpty()) {
            displayMenu(scanner);  // Отображаем подменю
        }
    }

    public void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=== " + title + " ===");
            int optionNumber = 1;
            for (MenuItem item : subItems) {
                if (item.isTitle) {
                    System.out.println(item.title); // Просто отображаем заголовок
                } else {
                    System.out.println(optionNumber++ + ". " + item.title);
                }
            }
            System.out.println("0. Назад");
            System.out.print("Выберите пункт: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка ввода

            if (choice == 0) {
                return; // Возврат к предыдущему меню
            }

            if (choice > 0 && choice <= optionNumber - 1) {
                subItems.get(choice - 1).execute(scanner);
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
