package geeksforgeeks.basic;

import java.util.Scanner;
import java.util.Stack;

public class LeadersInAnArray {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            printLeaders(arr);
        }
        scan.close();
    }

    private static void printLeaders(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int max = arr[arr.length - 1];
        stack.push(max);
        for (int i = arr.length - 2; i >= 0; i--) {
            if(arr[i] > max) {
                max = arr[i];
                stack.push(arr[i]);
            }
        }
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

}
