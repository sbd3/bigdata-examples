package geeksforgeeks.hard;

import java.util.Scanner;
import java.util.Stack;

public class LongestValidParentheses {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            process(scan.next());
        }
        scan.close();
    }

    static void process(String str) {
        int n = str.length();

        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '(') {
                stk.push(i);
            } else {
                stk.pop();
                if (!stk.empty())
                    result = Math.max(result, i - stk.peek());
                else
                    stk.push(i);
            }
        }
        System.out.println(result);
    }

}
