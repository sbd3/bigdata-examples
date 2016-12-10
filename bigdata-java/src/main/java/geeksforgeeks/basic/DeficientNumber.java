package geeksforgeeks.basic;

import java.util.Scanner;

public class DeficientNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int n = scan.nextInt();
            System.out.println(process(n));
        }
        scan.close();
    }

    private static int process(int n) {
        long sum = 1;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0 && (isPrime(i) || i == n)) {
                sum += i;
            }
        }
        return (2 * n) - sum > 0 ? 1 : 0;
    }

    private static boolean isPrime(int n) {
        boolean flag = false;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                flag = true;
            }
        }
        return flag;
    }

}
