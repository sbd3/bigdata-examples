package geeksforgeeks.basic;

import java.util.Scanner;

public class PadovanSequence {

    public static void main(String[] args) {
        int[] arr = new int[101];
        arr[0] = arr[1] = arr[2] = 1;
        for (int j = 3; j < arr.length; j++) {
            arr[j] = (arr[j-2] + arr[j-3]) % 1000000007;
        }
        
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            System.out.println(arr[scan.nextInt()]);
        }
        scan.close();
    }
}
