package geeksforgeeks.easy;

import java.util.Scanner;

public class NextLargerElement {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            processArr(arr);
        }
        scan.close();
    }

    private static void processArr(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] > arr[i]) {
                   System.out.print(arr[j] + " ");
                   flag = true;
                   break;
                }
            }
            if(!flag) {
                System.out.print("-1 ");
            }
        }
        System.out.print("-1 ");
        System.out.println();
    }

}
