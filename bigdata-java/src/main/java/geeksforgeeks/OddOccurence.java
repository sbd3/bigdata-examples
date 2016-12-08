package geeksforgeeks;

import java.util.Scanner;

public class OddOccurence {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            process(arr);
        }
        scan.close();
    }

    private static void process(int[] arr) {
        int num = arr[0];
        for (int i = 1; i < arr.length; i++) {
            num = num ^ arr[i];
        }
        System.out.println(num);
    }

}
