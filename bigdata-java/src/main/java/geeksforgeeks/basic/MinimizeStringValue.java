package geeksforgeeks.basic;

import java.util.Scanner;

public class MinimizeStringValue {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            String str = scan.next();
            int rem = scan.nextInt();
            findMinVal(str, rem);
        }
        scan.close();
    }

    private static void findMinVal(String str, int rem) {
        str = str.toLowerCase();
        int[] arr = initArray();
        for (char c : str.toCharArray()) {
            arr[c - 97] += 1;
        }
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            
            sum += Math.pow(arr[i], 2);
        }
        System.out.println(sum);
    }

    private static int[] initArray() {
        int[] arr = new int[26];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        return arr;
    }

}
