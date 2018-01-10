package hackeearth.basic.programming.algo;

import java.util.Scanner;

public class QuickSort {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        printArr(arr);
        quick_sort(arr, 0, arr.length - 1);
        printArr(arr);
        scan.close();
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void quick_sort(int[] arr, int start, int end) {
        if (start < end) {
            int position = partition(arr, start, end);
            quick_sort(arr, start, position - 1);
            quick_sort(arr, position + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int i = start + 1;
        int piv = arr[start];
        for (int j = start + 1; j <= end; j++) {
            if (arr[j] < piv) {
                swap(arr, i, j);
                i += 1;
            }
        }
        swap(arr, start, i - 1);
        return i - 1;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
