package geeksforgeeks.basic;

import java.util.Scanner;

public class SubArrayWithGivenSum {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCases = scan.nextInt();
        for (int i = 0; i < testCases; i++) {
            int[] arr = new int[scan.nextInt()];
            int sum = scan.nextInt();
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            processArr(arr, sum);
        }
        scan.close();
    }

    private static void processArr(int[] arr, int sum) {
        for (int i = 0; i < arr.length - 1; i++) {
            int curr_sum = 0;
            if(arr[i] > sum) {
                continue;
            } else {
                curr_sum = arr[i];
            }
            for (int j = i + 1; j < arr.length; j++) {
                curr_sum += arr[j];
                if(curr_sum == sum) {
                    System.out.println((i + 1) + " " + (j + 1));
                    return;
                }
            }
        }
        System.out.println(-1);
    }
}
