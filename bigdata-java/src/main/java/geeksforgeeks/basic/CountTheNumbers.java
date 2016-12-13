package geeksforgeeks.basic;

import java.util.Scanner;

public class CountTheNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            printCount(scan.nextInt());
        }
        scan.close();
    }

    private static void printCount(int nextInt) {
        int count = 0;
        for (int i = 1; i <= nextInt; i++) {
            String str = String.valueOf(i);
            if(str.contains("6") ||str.contains("7") || str.contains("8") || str.contains("9") || str.contains("0")) {
                continue;
            }
            count++;
        }
        System.out.println(count);
    }

}
