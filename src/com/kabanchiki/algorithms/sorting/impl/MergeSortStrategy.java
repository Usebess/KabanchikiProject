package com.kabanchiki.algorithms.sorting.impl;

import com.kabanchiki.algorithms.sorting.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @param <T> принимает классы реализующие интерфейс {@link Comparable}
 * Опционально принимает {@link Comparator} по которому будет происходить сортировка.
 * Временная сложность алгоритма O(n log n) пространственная O(n)
 */
public class MergeSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {

    private Comparator<T> comparator = null;

    public MergeSortStrategy() {
    }

    public MergeSortStrategy(Comparator<T> sortedBy) {
        this.comparator = sortedBy;
    }

    @Override
    public List<T> sort(List<T> list) {
        return mergeSort(list);
    }

    private Comparator<T> getComparator() {
        return (comparator != null) ? comparator : Comparable::compareTo;
    }

    private List<T> mergeSort(List<T> list) {
        // Базовый случай: если список содержит 0 или 1 элемент, он уже отсортирован
        if (list.size() <= 1) {
            return list;
        }

        // Разделяем список на две части
        int middle = list.size() / 2;
        List<T> left = list.subList(0, middle);
        List<T> right = list.subList(middle, list.size());

        // Рекурсивно сортируем обе части и объединяем их
        return merge(mergeSort(left), mergeSort(right));
    }

    private List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        // Определяем метод сравнения
        Comparator<T> cmp = getComparator();

        // Сравниваем элементы из обеих частей и добавляем наименьший в результат
        while (leftIndex < left.size() && rightIndex < right.size()) {
            result.add(cmp.compare(left.get(leftIndex), right.get(rightIndex)) <= 0 ? left.get(leftIndex++) : right.get(rightIndex++));
        }

        // Добавляем оставшиеся элементы из левой части (если есть)
        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex++));
        }

        // Добавляем оставшиеся элементы из правой части (если есть)
        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex++));
        }
        return result;
    }

}
