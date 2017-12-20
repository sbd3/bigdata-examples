package hackeearth.basic.programming.algo;

import java.util.Scanner;

public class TernarySearch {

    private static double func(double x) {
        return 2 * x * x - 12 * x + 7;
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int arr[][] = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = scan.nextInt();
            arr[i][1] = scan.nextInt();
        }

        for (int i = 0; i < N; i++) {
            System.out.println(mints(arr[i][0], arr[i][1]));
        }
        scan.close();
    }

    static int mints(double start, double end) {
        double l = start, r = end;
        for (int i = 0; i < 200; i++) {
            double l1 = (l * 2 + r) / 3;
            double l2 = (l + 2 * r) / 3;
            if (func(l1) < func(l2))
                r = l2;
            else
                l = l1;
        }
        double x = l;
        return (int) Math.round(func(x));
    }

    static int maxts(double start, double end) {
        double l = start, r = end;
        for (int i = 0; i < 200; i++) {
            double l1 = (l * 2 + r) / 3;
            double l2 = (l + 2 * r) / 3;
            if (func(l1) > func(l2))
                r = l2;
            else
                l = l1;
        }
        double x = l;
        return (int) Math.round(func(x));
    }

}
