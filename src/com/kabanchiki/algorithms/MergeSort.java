package algorithms.sorting;

import com.kabanchiki.Car;

public class MergeSort {
    static void merge(int[] array, int left, int middle, int right, int[] bufferArray){
        int pointerFirst = left;
        int pointerSecond = middle;
        int resultPointer = 0;
        while ((pointerFirst < middle) || (pointerSecond < right)) {
            if (pointerFirst == middle) {
                bufferArray[resultPointer++] = array[pointerSecond++];
                continue;
            }
            if (pointerSecond == right) {
                bufferArray[resultPointer++] = array[pointerFirst++];
                continue;
            }
            if (array[pointerFirst] < array[pointerSecond]) {
                bufferArray[resultPointer++] = array[pointerFirst++];
            } else {
                bufferArray[resultPointer++] = array[pointerSecond++];
            }
        }
        for (int i = left; i < right; i++)
        {
            array[i] = bufferArray[i - left];
        }

    }

    static void sort(int[] array, int left, int right, int[] bufferArray) {
        if (right - left <= 1) {
            return;
        }
        int middle = (left + right) / 2;
        sort(array, left, middle, bufferArray);
        sort(array, middle, right, bufferArray);
        merge(array, left, middle, right, bufferArray);
    }

    public static void mergeSort(int[] array){
        int[] bufferArray = new int[array.length];
        sort(array, 0, array.length, bufferArray);
    }

}
