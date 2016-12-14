package geeksforgeeks.basic;

import java.util.Scanner;

public class FindPositionOfSetBit {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            printposition(Integer.toBinaryString(scan.nextInt()).toCharArray());
        }
        scan.close();
    }

    private static void printposition(char[] charArray) {
        int count = 0, index = -1;
        for (int i = charArray.length - 1; i >= 0; i--) {
            if(charArray[i] == '1') {
                count++;
                index = (count == 1) ? charArray.length - i : -1;
            }
        }
        System.out.println(index);
    }

}
