package geeksforgeeks.medium;

import java.util.Scanner;
import java.util.Stack;

public class MaxAreaHistogram {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            // bruteForce(arr);
            stackVersion(arr);
        }
        scan.close();
    }

    static void stackVersion(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                stack.push(i);
            } else {
                int rightIndex = stack.pop();
                int leftIndex = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, arr[rightIndex] * leftIndex);
            }
        }
        while(!stack.isEmpty()) {
            int rightIndex = stack.pop();
            //int leftIndex = stack.isEmpty() ? i : i - stack.peek() - 1;
            //maxArea = Math.max(maxArea, arr[rightIndex] * leftIndex);
        }
        System.out.println(maxArea);
    }

    static void bruteForce(int[] arr) {
        int maxArea = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftIndex = findMinIndex(arr, i);
            int rightIndex = findMaxIndex(arr, i);
            // System.out.println(i+":: "+leftIndex + ": "+ rightIndex);
            maxArea = Math.max(maxArea, arr[i] * (rightIndex - leftIndex + 1));
        }
        System.out.println(maxArea);
    }

    static int findMaxIndex(int[] arr, int i) {
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[i])
                return j - 1;
        }
        return arr.length - 1;
    }

    static int findMinIndex(int[] arr, int i) {
        for (int j = i - 1; j >= 0; j--) {
            if (arr[j] < arr[i]) {
                return j + 1;
            }
        }
        return 0;
    }
}
