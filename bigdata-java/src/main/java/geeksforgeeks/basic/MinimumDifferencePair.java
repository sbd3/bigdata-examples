package geeksforgeeks.basic;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumDifferencePair {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            printMinDiff(arr);
        }
        scan.close();
    }

    private static void printMinDiff(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = Math.abs(arr[i] - arr[i+1]);
            if(diff < min) {
                min = diff;
            }
        }
        System.out.println(min);
    }

}
