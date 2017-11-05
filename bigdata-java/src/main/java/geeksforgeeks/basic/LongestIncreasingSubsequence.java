package geeksforgeeks.basic;

import java.util.Scanner;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            findLongestSubseq(arr);
        }
        scan.close();
    }

    //   i          j
    // {10, 22, 9, 33, 21, 50, 41, 60, 80}
    // { 1,  2, 1,  1,  1,  1,  1,  1,  1}
    private static void findLongestSubseq(int[] arr) {
        int[] lis = new int[arr.length];
        for (int i = 0; i < lis.length; i++) {
            lis[i] = 1;
        }
        for (int j = 1; j < arr.length; j++) {
            for (int i = 0; i < j; i++) {
                if(arr[j] > arr[i] && lis[j] < lis[i] + 1) {
                    lis[j] = lis[i] + 1;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lis.length; i++) {
            if(lis[i] > max)
                max = lis[i];
        }
        if(max == Integer.MIN_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(max);
        }
    }
}
