package geeksforgeeks.basic;

import java.util.Scanner;

public class SumOfDigitsDivisibility {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            String str = scan.next();
            checkDivisibility(str);
        }
        scan.close();
    }

    private static void checkDivisibility(String str) {
        char[] arr = str.toCharArray();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += ((int) arr[i] - 48);
        }
        if(Integer.parseInt(str) % sum == 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    

}
