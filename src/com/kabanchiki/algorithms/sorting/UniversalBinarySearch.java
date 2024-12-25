package algorithms.searching;

import java.util.List;
import java.util.function.Function;

public class UniversalBinarySearch {
    public static <T, K> int binarySearch(List<T> list, K search, Function<T, Comparable<K>> keyExtractor){
        int left = 0;
        int right = list.size() - 1;
        int middle = (left + right) / 2;

        T middleElement = list.get(middle);

        int key = keyExtractor.apply(middleElement).compareTo(search);

        while (left <= right) {
            if (key < 0) {
                left = middle + 1;
            } else if (key > 0) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
