package geeksforgeeks.basic;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumTipCalculator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int N = scan.nextInt();
            int X = scan.nextInt();
            int Y = scan.nextInt();
            int[] A = new int[N];
            int[] B = new int[N];
            for (int j = 0; j < N; j++) {
                A[j] = scan.nextInt();
            }
            for (int j = 0; j < N; j++) {
                B[j] = scan.nextInt();
            }
            Tip[] tips = convertArr(A, B);
            processTip(tips, N, X, Y);
        }
        scan.close();
    }
    
    private static Tip[] convertArr(int[] A, int[] B) {
        Tip[] arr = new Tip[A.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Tip(A[i], B[i]);
        }
        return arr;
    }

    private static void processTip(Tip[] tips, int n, int x, int y) {
        Arrays.sort(tips);
        int sum = 0;
        for (int i = 0; i < tips.length; i++) {
            if(tips[i].diff <= 0 && y > 0) {
                sum += tips[i].B;
                y--;
            } else if(tips[i].diff >= 0 && x > 0) {
                sum += tips[i].A;
                x--;
            }
        }
        System.out.println(sum);
    }
    
    static class Tip implements Comparable<Tip> {
        int A;
        int B;
        int diff;

        public Tip(int a, int b) {
            A = a;
            B = b;
            diff = A - B;
        }

        @Override
        public int compareTo(Tip o) {
            if (diff > o.diff) {
                return 1;
            } else if (diff == o.diff) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}

