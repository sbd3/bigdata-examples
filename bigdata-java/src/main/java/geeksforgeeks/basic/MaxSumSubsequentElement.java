package geeksforgeeks.basic;

import java.util.Scanner;

/*  
 * Given an array containing both negative and positive integers. 
 * Find the contiguous sub-array with maximum sum.
*/
public class MaxSumSubsequentElement {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            maxSubArraySum(arr);
        }
        scan.close();
    }

    static void maxSubArraySum(int arr[]) {
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
        for (int i = 0; i < arr.length; i++) {
            max_ending_here = max_ending_here + arr[i];
            max_so_far = Math.max(max_so_far, max_ending_here);
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        System.out.println(max_so_far);
    }
}
