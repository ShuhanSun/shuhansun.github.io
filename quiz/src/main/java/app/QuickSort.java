package app;

import java.util.Arrays;

public class QuickSort {

    // write a quick sort function
    public static void main(String[] args) {
        test(new int[]{5, 0, 1, 8, 9, 3, 7, 4, 2});
        test(new int[]{5});
        test(new int[]{5, 1});
        test(new int[]{1, 5});
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        test(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0});
        test(new int[]{9, 1, 1, 1, 1, 1, 1, 1, 0});
        test(new int[]{9, 1, 1, 1, 1, 1, 1, 1, 1});
        test(new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1});
        test(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1});
        test(new int[]{1, 9, 9, 9, 9, 9, 9, 9, 1});
        test(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 1});
        test(new int[]{1, 9, 2, 8, 6, 5, 5, 4, 3, 1, 9});
    }

    static void test(int[] array) {
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] < array[i]) {
                System.out.println("wrong sort!!!^^^");
            }
        }
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = partitionEasy(array, start, end); // swap the items to the left which is less than pivot, and right for the bigger
        quickSort(array, start, pivot - 1);
//        quickSort(array, pivot, end);//partitionGit
        quickSort(array, pivot + 1, end);
    }

    private static int partitionEasy(int[] array, int left, int right) {
        int pivot = left;
        int indexOfLessPi = left;
        for (int i = left + 1; i <= right; i++) {
            if (array[i] < array[pivot]) {
                indexOfLessPi++;
                swap(array, i, indexOfLessPi);
            }
        }
        swap(array, pivot, indexOfLessPi);
        return indexOfLessPi;
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left + 1, j = right;
        while (true) {//FIXME: wrong
            // i -->, to find i item >= pivot
            while (array[i] < pivot) {
                i++;
            }
            //  <-- j, to find j item < pivot
            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                swap(array, i, j);
            }
            else {
                break;
            }
        }

        swap(array, left, i); // put pivot at the middle
        return i;// pivot is the left most element if multiple elements are equal
    }

    public static int partitionGit(int arr[], int left, int right) {//wrong?
        int pivot = arr[(left + right) / 2]; // Pick a pivot point. Can be an element.

        while (left <= right) { // Until we've gone through the whole array
            // Find element on left that should be on right
            while (arr[left] < pivot) {
                left++;
            }

            // Find element on right that should be on left
            while (arr[right] > pivot) {
                right--;
            }

            // Swap elements, and move left and right indices
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static int partitionInBook(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left, j = right + 1;
        while (true) {
            // i -->, to find i item >= pivot
            while (array[++i] < pivot) {
                if (i == right) {
                    break;
                }
            }
            //  <-- j, to find j item <= pivot
            while (array[--j] > pivot) {
                if (j == left) {
                    break;
                }
            }

            if (i < j) {
                swap(array, i, j);
            }
            else {
                break;
            }
        }

        swap(array, left, j); // put pivot at the middle
        return j;
    }

    private static void swap(int[] array, int start, int pivot) {
        int t = array[start];
        array[start] = array[pivot];
        array[pivot] = t;
    }

}
