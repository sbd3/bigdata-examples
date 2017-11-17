package hackeearth.medium.programming.io;

import java.util.Scanner;

public class TrailingZeroesInFactorial {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            long noOfZeroes = scan.nextLong();
            if(noOfZeroes % 5 == 0) {
                System.out.println(0);
            } else {
                long val = 0;
                if(noOfZeroes / 5 > 0) {
                    val = (noOfZeroes - (noOfZeroes / 5) + 1) * 5;
                } else {
                    val = (noOfZeroes - (noOfZeroes / 5)) * 5;
                }
                System.out.println(5);
                for (int j = 0; j < 5; j++) {
                    System.out.print(val + j + " ");
                }
                System.out.println();
            }
        }
        scan.close();
        //countZeros();
    }
    
    static void countZeros() {
        for (int i = 1; i < 22; i++) {
            System.out.println(i +":" + numOfZeros(i));
        }
    }
    
    static int numOfZeros(int num) {
        long fact = factorial(num);
        int count = 0;
        while(fact % 10 == 0) {
            count++;
            fact = fact / 10;
        }
        return count;
    }
    
    static long factorial(int num) {
        if(num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }
    
}
