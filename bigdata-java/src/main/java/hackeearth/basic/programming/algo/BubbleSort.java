package hackeearth.basic.programming.algo;

import java.util.Scanner;

public class BubbleSort {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = scan.nextInt();
        }
        System.out.println(bubbleSort(arr));
        scan.close();
    }

    private static int bubbleSort(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    // Swap elements
                    arr[j] = arr[j] + arr[j + 1];
                    arr[j + 1] = arr[j] - arr[j + 1];
                    arr[j] = arr[j] - arr[j + 1];
                    count++;
                }
            }
        }
        return count;
    }

}
