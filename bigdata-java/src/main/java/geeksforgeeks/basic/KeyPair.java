package geeksforgeeks.basic;

import java.util.Scanner;

public class KeyPair {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCases = scan.nextInt();
        for (int i = 0; i < testCases; i++) {
            int[] arr = new int[scan.nextInt()];
            int val = scan.nextInt();
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            if(processArr(arr, val)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        scan.close();
    }
    
    static boolean processArr(int[] arr, int val) {
        for (int j = 0; j < arr.length - 1; j++) {
            for (int j2 = j + 1; j2 < arr.length; j2++) {
                if(arr[j] + arr[j2] == val) {
                    return true;
                }
            }
        }
        return false;
    }
}
