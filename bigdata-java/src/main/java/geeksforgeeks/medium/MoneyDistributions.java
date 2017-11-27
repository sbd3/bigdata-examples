package geeksforgeeks.medium;

import java.util.Scanner;

public class MoneyDistributions {
public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            long n = scan.nextInt();
            long k = scan.nextInt();
            long mod = 1000000007;
            long mul = 1;
            while(k > 1) {
                mul = (mul * (((n-1) % mod) * ((k-1) % mod))) % mod;
                k--;
            }
            System.out.println(mul);
        }
        scan.close();
    }
}
