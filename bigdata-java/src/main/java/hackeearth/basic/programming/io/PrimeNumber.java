package hackeearth.basic.programming.io;

import java.util.Scanner;

public class PrimeNumber {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();                 // Reading input from STDIN
        for(int i = 2; i <= n; i++) {
            if(isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        s.close();
    }
    
    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }

}
