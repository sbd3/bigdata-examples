package geeksforgeeks;

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            process(scan.next().toCharArray());
        }
        scan.close();
    }

    private static void process(char[] next) {
        for (int i = 0; i < next.length / 2; i++) {
            if (next[i] != next[next.length - i - 1]) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}
