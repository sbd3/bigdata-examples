package geeksforgeeks.basic;

import java.util.Scanner;

public class MedianStream {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = 0, t = 0, j = 0;
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            j = i;
            while (j > 0 && a[j] < a[j - 1]) {
                t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
                j--;
            }
            if ((i + 1) % 2 == 0) {
                m = (a[i / 2] + a[(i + 1) / 2]) / 2;
            } else
                m = a[i / 2];
            System.out.println(m);
        }
        sc.close();
    }

}
