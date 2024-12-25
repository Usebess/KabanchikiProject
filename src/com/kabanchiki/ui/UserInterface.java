package com.kabanchiki.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserInterface extends JFrame {
    private List<Integer> array = new ArrayList<>();

    public UserInterface() {
        setTitle("Главное меню");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        JButton dataSourceButton = new JButton("1. Выбор источника данных для массива");
        JButton sortingButton = new JButton("2. Сортировка массива и бинарный поиск");
        JButton carSortingButton = new JButton("3. Кастомная сортировка Car");
        JButton rootSortingButton = new JButton("4. Кастомная сортировка Root");
        JButton bookSortingButton = new JButton("5. Кастомная сортировка Book");
        JButton exitButton = new JButton("6. Выход");
/*
        //Сами кнопки на сортировку
        dataSourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseDataSource();
            }
        });

        sortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortArray();
            }
        });

        carSortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customSortCar();
            }
        });

        rootSortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customSortRoot();
            }
        });

        bookSortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customSortBook();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
*/
        add(dataSourceButton);
        add(sortingButton);
        add(carSortingButton);
        add(rootSortingButton);
        add(bookSortingButton);
        add(exitButton);
    }

    private void chooseDataSource() {
        String[] options = {"Файл", "Рандом", "Ручной ввод"};
        String choice = (String) JOptionPane.showInputDialog(null, "Выберите источник:",
                "Выбор источника данных", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != null) {
            String lengthInput = JOptionPane.showInputDialog("Введите длину массива:");
            if (lengthInput != null) {
                int length = Integer.parseInt(lengthInput);
                fillArray(choice, length);
            }
        }
    }

    private void fillArray(String source, int length) {
        array.clear();
        for (int i = 0; i < length; i++) {
            if (source.equals("Файл")) {
                // Логика для заполнения из файла
            } else if (source.equals("Рандом")) {
                array.add((int) (Math.random() * 100)); // Рандомные числа
            } else if (source.equals("Ручной ввод")) {
                String numInput = JOptionPane.showInputDialog("Введите число:");
                if (numInput != null) {
                    array.add(Integer.parseInt(numInput));
                }
            }
        }
    }
}