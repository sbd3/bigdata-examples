package geeksforgeeks.basic;

import java.util.Scanner;

public class SetBits {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int n = scan.nextInt();
            printBits(n);
        }
        scan.close();
    }

    private static void printBits(int n) {
        String str = Integer.toBinaryString(n);
        int count = 0;
        for (char c : str.toCharArray()) {
            if(c == '1') {
                count++;
            }
        }
        System.out.println(count);
    }
}
