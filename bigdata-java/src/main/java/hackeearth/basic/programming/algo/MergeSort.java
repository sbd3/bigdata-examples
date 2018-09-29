package hackeearth.basic.programming.algo;

import java.util.Scanner;

public class MergeSort {

    private static long count = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        merge_sort(arr);
        System.out.println(count);
        scan.close();
    }

    private static void merge_sort(int[] a) {
        int[] aux = new int[a.length];
        merge_sort(a, aux, 0, a.length - 1);
    }

    private static void merge_sort(int[] arr, int[] aux, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            merge_sort(arr, aux, start, mid);
            merge_sort(arr, aux, mid + 1, end);
            merge(arr, aux, start, mid, end);
        }
    }

    private static void merge(int[] arr, int[] aux, int start, int mid, int end) {
        int i = start, j = mid + 1;
        for (int k = start; k <= end; k++) {
            aux[k] = arr[k];
        }
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > end) {
                arr[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
                count += (long)(mid - i + 1);
            }
        }
    }

}
