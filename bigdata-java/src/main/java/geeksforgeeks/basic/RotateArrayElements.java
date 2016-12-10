package geeksforgeeks.basic;

import java.util.Scanner;

public class RotateArrayElements {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            int rotations = scan.nextInt() % arr.length;
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            for (int j = rotations; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            for (int j = 0; j < rotations; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
        }
        scan.close();
    }

}
