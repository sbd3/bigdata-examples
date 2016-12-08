package geeksforgeeks;

import java.util.Scanner;

/**
 * Loop for all elements in arr[]. Maintain two sums incl and excl where incl =
 * Max sum including the previous element and excl = Max sum excluding the
 * previous element.
 * 
 * Max sum excluding the current element will be max(incl, excl) and max sum
 * including the current element will be excl + current element (Note that only
 * excl is considered because elements cannot be adjacent).
 * 
 * At the end of the loop return max of incl and excl.
 * 
 * @author Puneet.Singh
 * @version 1.0.0
 */
public class MaxSumNoSubsequentElement {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            printMaxSum(arr);
        }
        scan.close();
    }

    private static void printMaxSum(int[] arr) {
        int incl = arr[0];
        int excl = 0;
        int excl_new;
        for (int i = 1; i < arr.length; i++) {
            excl_new = Math.max(excl, incl);
            incl = excl + arr[i];
            excl = excl_new;
        }
        System.out.println(Math.max(incl, excl));
    }

}
