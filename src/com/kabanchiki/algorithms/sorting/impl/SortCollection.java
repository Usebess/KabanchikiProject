package com.kabanchiki.algorithms.sorting.impl;

import java.util.Comparator;
import java.util.List;

/**
 * Утилитный класс сортировки
 */
public final class SortCollection {

    private SortCollection() {}

    /**
     * Сортирует по переданному {@link Comparator<T>}
     * @return Возвращает отсортированный список
     * @param <T> принимает классы реализующие интерфейс {@link Comparable<T>}
     * @param sortedBy принимает {@link Comparator} для сортировки
     */
    public
    static <T extends Comparable<T>> List<T> mergeSort(List<T> list, Comparator<T> sortedBy) {
        return new MergeSortStrategy<>(sortedBy).sort(list);
    }

    /**
     * Сортирует по умолчанию
     * @return Возвращает отсортированный список
     * @param <T> принимает классы реализующие интерфейс {@link Comparable}
     */
    public
    static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        return new MergeSortStrategy<T>().sort(list);
    }

    /**
     * Сортирует массивы
     * @param src Исходный массив
     * @param sortedBy
     * @return Возвращает тот же массив
     * @param <T>
     */
    public
    static <T extends Comparable<T>> T[] mergeSort(T[] src, Comparator<T> sortedBy) {
        MergeSortArray.mergeSort(src, sortedBy);
        return src;
    }

}
