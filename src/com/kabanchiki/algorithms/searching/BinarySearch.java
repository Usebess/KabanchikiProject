package com.kabanchiki.algorithms.searching;

import java.util.List;
import java.util.function.Function;

public class BinarySearch {

    /**
     * Бинарный поиск для списка.
     * @param <T>           Тип элементов списка.
     * @param <K>           Тип ключа, по которому выполняется поиск.
     * @param list          Отсортированный список элементов.
     * @param target        Целевое значение, которое нужно найти.
     * @param keyExtractor  Функция, извлекающая ключ из элемента списка.
     * @return Индекс найденного элемента в списке или -1, если элемент не найден.
     */
    public static <T, K> int binarySearch(List<T> list, K target, Function<T, Comparable<K>> keyExtractor) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            T middleElement = list.get(middle); // Получаем элемент в середине

            // Извлекаем ключ с помощью keyExtractor
            int comparison = keyExtractor.apply(middleElement).compareTo(target);

            if (comparison == 0) {
                return middle; // Элемент найден
            } else if (comparison < 0) {
                left = middle + 1; // Искать в правой части
            } else {
                right = middle - 1; // Искать в левой части
            }
        }

        return -1; // Элемент не найден
    }
}
