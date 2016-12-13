package geeksforgeeks.basic;

import java.util.Scanner;

public class KeypadTyping {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            char[] arr = scan.next().toLowerCase().toCharArray();
            printString(arr);
        }
        scan.close();
    }

    private static void printString(char[] arr) {
        for (char c : arr) {
            switch (c) {
            case 'a':
            case 'b':
            case 'c':
                System.out.print(2);
                break;
            case 'd':
            case 'e':
            case 'f':
                System.out.print(3);
                break;
            case 'g':
            case 'h':
            case 'i':
                System.out.print(4);
                break;
            case 'j':
            case 'k':
            case 'l':
                System.out.print(5);
                break;
            case 'm':
            case 'n':
            case 'o':
                System.out.print(6);
                break;
            case 'p':
            case 'q':
            case 'r':
            case 's':
                System.out.print(7);
                break;
            case 't':
            case 'u':
            case 'v':
                System.out.print(8);
                break;
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                System.out.print(9);
                break;
            }
        }
        System.out.println();
    }

}
