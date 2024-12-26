package com.kabanchiki.algorithms.sorting.impl;

import com.kabanchiki.algorithms.sorting.SortStrategy;

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
        T[] array = list.toArray((T[]) new Comparable[list.size()]);
        mergeSort(array, 0, array.length - 1, getComparator());
        return List.of(array);
    }

    private Comparator<T> getComparator() {
        return (comparator != null) ? comparator : Comparable::compareTo;
    }

    // Метод слияния для массивов с обобщениями
    private void merge(T[] array, int left, int middle, int right, Comparator<T> comparator) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        // Создаем временные массивы для левой и правой части
        T[] leftArray = (T[]) new Comparable[leftSize];
        T[] rightArray = (T[]) new Comparable[rightSize];

        // Копируем элементы в временные массивы
        System.arraycopy(array, left, leftArray, 0, leftSize);
        System.arraycopy(array, middle + 1, rightArray, 0, rightSize);

        int i = 0, j = 0, k = left;

        // Слияние двух отсортированных частей
        while (i < leftSize && j < rightSize) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы из левого массива
        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Копируем оставшиеся элементы из правого массива
        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Рекурсивная сортировка слиянием
    private void mergeSort(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(array, left, middle, comparator); // Сортировка левой половины
            mergeSort(array, middle + 1, right, comparator); // Сортировка правой половины

            // Слияние отсортированных частей
            merge(array, left, middle, right, comparator);
        }
    }

}
