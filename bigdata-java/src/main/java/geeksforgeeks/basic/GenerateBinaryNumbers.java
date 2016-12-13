package geeksforgeeks.basic;

import java.util.Scanner;
import java.util.Stack;

public class GenerateBinaryNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            //printBinaryNumbers(scan.nextInt());
            printBinaryNumbers2(scan.nextInt());
        }
        scan.close();
    }

    private static void printBinaryNumbers2(int nextInt) {
        for (int i = 1; i <= nextInt; i++) {
            int temp = i;
            Stack<Integer> stack = new Stack<>();
            while(temp > 0) {
                stack.push(temp % 2);
                temp = temp / 2;
            }
            while(!stack.isEmpty()) {
                System.out.print(stack.pop());
            }
            System.out.print(" ");
        }
        System.out.println();
    }

    private static void printBinaryNumbers(int nextInt) {
        for (int i = 1; i <= nextInt; i++) {
            System.out.print(Integer.toBinaryString(i) + " ");
        }
        System.out.println();
    }

}
