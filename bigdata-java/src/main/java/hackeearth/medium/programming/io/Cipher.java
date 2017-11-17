package hackeearth.medium.programming.io;

import java.util.Scanner;

public class Cipher {

    // 65 - 90, 97 - 122
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] arr = scan.next().toCharArray();
        int num = scan.nextInt();
        for (char c : arr) {
            if (c >= 65 && c <= 90) {
                int val = c + (num % 26);
                if (val > 90)
                    val = 65 + val - 91;
                System.out.print((char) val);
            } else if (c >= 97 && c <= 122) {
                int val = c + (num % 26);
                if (val > 122)
                    val = 97 + val - 123;
                System.out.print((char) val);
            } else if (c >= 48 && c <= 57) {
                int val = c + (num % 10);
                if (val > 57) {
                    val = 48 + val - 58;
                }
                System.out.print((char) val);
            } else {
                System.out.print(c);
            }
        }
        scan.close();
    }
}
