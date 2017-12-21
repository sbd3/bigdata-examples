package hackeearth.basic.programming.algo;

import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()];
        int n = scan.nextInt();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }

        selectionSort(arr, n);

        for (int i : arr) {
            System.out.print(i + " ");
        }
        scan.close();
    }

    private static void selectionSort(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
