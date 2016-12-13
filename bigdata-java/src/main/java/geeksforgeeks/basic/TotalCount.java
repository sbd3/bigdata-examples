package geeksforgeeks.basic;

import java.util.Scanner;

public class TotalCount {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            int k = scan.nextInt();
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            calculateCount(arr, k);
        }
        scan.close();
    }

    private static void calculateCount(int[] arr, int k) {
        int count = 0;
        for (int i : arr) {
            count += i / k;
            if(i % k != 0)
                count += 1;
        }
        System.out.println(count);
    }

}
