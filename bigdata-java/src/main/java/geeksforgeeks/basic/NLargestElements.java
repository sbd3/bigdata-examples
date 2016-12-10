package geeksforgeeks.basic;

import java.util.Scanner;

/**
 * Modified bubble sort to iterate only n number of times.
 * @author psingh15
 *
 */
public class NLargestElements {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            int N = scan.nextInt();
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            process(arr, N);
        }
        scan.close();
    }

    private static void process(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = arr.length - 1; j > 0; j--) {
                if (arr[j] > arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
