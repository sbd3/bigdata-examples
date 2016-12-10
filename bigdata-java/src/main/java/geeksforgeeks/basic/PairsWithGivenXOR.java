package geeksforgeeks.basic;

import java.util.Scanner;

public class PairsWithGivenXOR {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            int x = scan.nextInt();
            bruteForce(arr, x);
        }
        scan.close();
    }

    private static void bruteForce(int[] arr, int x) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if(i != j && (arr[i] ^ arr[j]) == x) {
                    count++;
                }
            }
        }
        count = count % 2 == 0 ? count / 2 : count / 2 + 1;
        System.out.println(count);
    }

}
