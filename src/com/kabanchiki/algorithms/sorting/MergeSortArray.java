package com.kabanchiki.algorithms.sorting;

import java.util.Comparator;

public class MergeSortArray {

    private static <T> void merge(T[] array, int start, int mid, int end, Comparator<T> comparator) {
        int leftIndex = start;
        int rightIndex = mid;
        int tempIndex = 0;

        // Временный массив для хранения результата слияния
        T[] mergedArray = (T[]) new Object[end - start];

        while ((leftIndex < mid) || (rightIndex < end)) {
            if (leftIndex == mid) {
                mergedArray[tempIndex++] = array[rightIndex++];
                continue;
            }
            if (rightIndex == end) {
                mergedArray[tempIndex++] = array[leftIndex++];
                continue;
            }
            if (comparator.compare(array[leftIndex], array[rightIndex]) < 0) {
                mergedArray[tempIndex++] = array[leftIndex++];
            } else {
                mergedArray[tempIndex++] = array[rightIndex++];
            }
        }

        // Копирование результата обратно в исходный массив
        System.arraycopy(mergedArray, 0, array, start, mergedArray.length);
    }

    private static <T> void sort(T[] array, int start, int end, Comparator<T> comparator) {
        if (end - start <= 1) {
            return;
        }

        int mid = (start + end) / 2;
        sort(array, start, mid, comparator);
        sort(array, mid, end, comparator);
        merge(array, start, mid, end, comparator);
    }

    public static <T> void mergeSort(T[] array, Comparator<T> comparator) {
        sort(array, 0, array.length, comparator);
    }

}
