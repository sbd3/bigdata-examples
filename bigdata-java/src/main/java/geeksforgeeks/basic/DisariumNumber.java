package geeksforgeeks.basic;

import java.util.Scanner;

public class DisariumNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            String n = scan.next();
            char[] arr = n.toCharArray();

            int sum = 0;
            for (int j = 0; j < arr.length; j++) {
                int number = arr[j] - 48;
                sum += Math.pow(number, j + 1);
            }
            if (sum == Integer.parseInt(n)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
        scan.close();
    }

}
