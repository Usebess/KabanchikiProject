package algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

public class Sorting<T extends Comparable<T>> {

    UniversalMergeSort<T> universalMergeSort;

    public Sorting(UniversalMergeSort<T> universalMergeSort) {
        this.universalMergeSort = universalMergeSort;
    }

    public void setUniversalMergeSort(UniversalMergeSort<T> universalMergeSort) {
        this.universalMergeSort = universalMergeSort;
    }

    public List<T> sort(List<T> list) {
        return universalMergeSort.sort(list);
    }
}
