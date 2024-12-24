package algorithms.searching;

public class BinarySearch {
    public static int binarySearch(int[] array, int search){
        int left = 0;
        int right = array.length - 1;
        int middle = (left + right) / 2;

        while (left <= right) {
            if (array[middle] < search) {
                left = middle + 1;
            } else if (array[middle] > search) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
