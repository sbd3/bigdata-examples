package hackeearth.basic.programming.io;

import java.util.Scanner;

public class CountDivisors {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int l = scan.nextInt();
        int r = scan.nextInt();
        int k = scan.nextInt();
        int count = 0;
        for (int i = l; i <= r; i++) {
            if(i % k == 0)
                count++;
        }
        System.out.println(count);
        scan.close();
    }
}
