package geeksforgeeks.basic;

import java.util.Scanner;

public class MissingNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt() - 1];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            boolean flag = true;
            for (int j = 0; j < arr.length - 1; j++) {
                if(arr[j] + 1 != arr[j + 1]) {
                    flag = false;
                    System.out.println(arr[j] + 1);
                }
            }
            if(arr[0] != 1) {
                System.out.println("1");
            } else if(flag) {
                System.out.println();
            }
        }
        scan.close();
    }
}
