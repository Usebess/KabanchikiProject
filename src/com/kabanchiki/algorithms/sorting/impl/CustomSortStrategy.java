package com.kabanchiki.algorithms.sorting.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @param <T> принимает классы реализующие интерфейс {@link Comparable}
 * - Для числового поля:
 * - Элементы с четными значениями сортируются в порядке указанном через {@link Comparator}.
 * - Элементы с нечетными значениями остаются на своих местах.
 */
public class CustomSortStrategy<T extends Comparable<T>> extends MergeSortStrategy<T> {

    /**
     * Функциональный интерфейс для извлечения целочисленных значений
     */
    private final Function<T, Integer> integerExtractor;

    /**
     * @param comparator Сортирует элементы по правилам {@link Comparator} через {@link MergeSortStrategy}
     * @param extractor Извлекает числа из поля класса для проверки на четность. Принимает только целые числа.
     */
    public CustomSortStrategy(Comparator<T> comparator, Function<T, Integer> extractor) {
        super(comparator);
        this.integerExtractor = extractor;
    }

    @Override
    public List<T> sort(List<T> items) {
        // Извлекаем четные элементы
        List<T> evenItems = new ArrayList<>();
        List<Integer> evenIndices = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            if (integerExtractor.apply(items.get(i)) % 2 == 0) {
                evenItems.add(items.get(i));
                evenIndices.add(i);
            }
        }

        // Сортируем четные элементы с помощью MergeSort
        List<T> evenItemsSort = super.sort(evenItems);

        // Возвращаем отсортированные четные элементы на исходные позиции
        for (int i = 0; i < evenIndices.size(); i++) {
            items.set(evenIndices.get(i), evenItemsSort.get(i));
        }
        return items;
    }

}
