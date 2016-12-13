package geeksforgeeks.basic;

import java.util.Scanner;

public class SumOfConsecutives {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            long x = sc.nextLong();
            System.out.println(isSumOfConsecutives(x));
            t--;
        }
        sc.close();
    }

    public static int isSumOfConsecutives(long x) {
        System.out.println("res: " + (x & (x - 1)));
        return ((x & (x - 1)) == 0) ? 0 : 1;
    }
}
