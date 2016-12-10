package geeksforgeeks.basic;

import java.util.Scanner;

public class ProductOfComplexNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            long[] real = new long[5];
            for (int j = 0; j < real.length; j++) {
                real[j] = scan.nextInt();
            }
            long[] imag = new long[5];
            for (int j = 0; j < imag.length; j++) {
                imag[j] = scan.nextInt();
            }
            computeProduct(real, imag);
        }
        scan.close();
    }

    private static void computeProduct(long[] real, long[] imag) {
        for (int i = 0; i < imag.length - 1; i++) {
            long re = real[i] * real[i + 1] - imag[i] * imag[i + 1];
            long im = real[i] * imag[i + 1] + real[i + 1] * imag[i];
            real[i + 1] = re;
            imag[i + 1] = im;
        }
        System.out.println(real[4] + " " + imag[4]);
    }

}
