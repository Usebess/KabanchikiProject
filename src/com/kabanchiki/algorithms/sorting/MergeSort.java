package algorithms.sorting;

import java.util.*;

public class MergeSort<T extends Comparable<T>> {

    public List<T> sort(List<T> list) {
        MergeSort ms = new MergeSort();

        List<T> sortedList = ms.mergeSort(list);
        return sortedList;
}

    public List<T> mergeSort(List<T> list) {
    List<T> left = new ArrayList<>();
    List<T> right = new ArrayList<>();
    int middle;
    if (list.size() == 1) {
        return list;
    } else {
        middle = list.size() / 2;
        for (int i = 0; i < middle; i++) {
            left.add(list.get(i));
        }
        for (int i = middle; i < list.size(); i++) {
            right.add(list.get(i));
        }
        left = mergeSort(left);
        right = mergeSort(right);
        merge(left, right, list);
    }
    return list;
}

    private void merge(List<T> left, List<T> right, List<T> list) {
        int pointerFirst = 0;
        int pointerSecond = 0;
        int pointerMain = 0;

        while(pointerFirst<left.size() && pointerSecond < right.size()) {

            if(left.get(pointerFirst).compareTo(right.get(pointerSecond)) < 0) {
                list.set(pointerMain, left.get(pointerFirst));
                pointerFirst++;
            } else {
                list.set(pointerMain, right.get(pointerSecond));
                pointerSecond++;
            }
            pointerMain++;
        }

        while(pointerFirst < left.size()) {
            list.set(pointerMain, left.get(pointerFirst));
            pointerMain++;
            pointerFirst++;
        }
        while(pointerSecond < right.size()) {
            list.set(pointerMain, right.get(pointerSecond));
            pointerMain++;
            pointerSecond++;
        }
    }
}
