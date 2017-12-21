package hackeearth.basic.programming.algo;

import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        int[] indexArr = insertionSort(arr);

        for (int i : indexArr) {
            System.out.print(i + 1 + " ");
        }
        scan.close();
    }

    private static int[] insertionSort(int[] arr) {
        int[] index = new int[arr.length];
        int[] aux = new int[arr.length];
        
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
            aux[i] = i;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j, j + 1);
                    swap(index, j, j+1);
                    swap(aux, index[j], index[j+1]);
                }
            }

        }
        return aux;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
