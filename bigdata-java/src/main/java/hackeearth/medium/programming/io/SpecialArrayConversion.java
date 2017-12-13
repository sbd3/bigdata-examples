package hackeearth.medium.programming.io;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SpecialArrayConversion {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        process(arr);
        scan.close();
    }

    private static void process(int[] arr) {
        ArrayList<Integer> primeNos = new ArrayList<>();
        ArrayList<Integer> nonPrimeNos = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (isPrime(arr[i])) {
                primeNos.add(arr[i]);
            } else {
                nonPrimeNos.add(arr[i]);
            }
        }
        primeNos.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2)
                    return 1;
                else if (o1 < o2)
                    return -1;
                else
                    return 0;
            }
        });
        nonPrimeNos.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2)
                    return -1;
                else if (o1 < o2)
                    return 1;
                else
                    return 0;
            }
        });
        primeNos.addAll(nonPrimeNos);
        for (Integer i : primeNos) {
            System.out.print(i + " ");
        }
    }

    public static boolean isPrime(int number) {
        if(number == 1) return false;
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 2; i < sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
