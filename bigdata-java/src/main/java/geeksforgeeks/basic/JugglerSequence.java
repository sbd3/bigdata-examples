package geeksforgeeks.basic;

import java.util.Scanner;

public class JugglerSequence {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            generateSequence(scan.nextInt());
        }
        scan.close();
    }

    private static void generateSequence(int nextInt) {
        System.out.print(nextInt + " ");
        while(nextInt > 1) {
            if(nextInt % 2 == 0) {
                nextInt = (int) Math.sqrt(nextInt);
                System.out.print(nextInt + " ");
            } else {
                nextInt = (int) Math.sqrt(Math.pow(nextInt, 3));
                System.out.print(nextInt + " ");
            }
        }
        System.out.println();
    }

}
