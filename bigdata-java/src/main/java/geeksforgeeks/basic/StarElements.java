package geeksforgeeks.basic;

import java.util.Scanner;
import java.util.Stack;

public class StarElements {

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
        Stack<Integer> stack = new Stack<>();
        int curr_max = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] > curr_max) {
                stack.push(arr[i]);
                curr_max = arr[i];
            }
        }
        if(curr_max == Integer.MIN_VALUE) {
            System.out.println(-1);
            System.out.println(-1);
        } else {
            while(!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
            System.out.println();
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] == curr_max) {
                    count++;
                }
            }
            if(count > 1) {
                System.out.println(-1);
            } else {
                System.out.println(curr_max);
            }
        }
    }
}
