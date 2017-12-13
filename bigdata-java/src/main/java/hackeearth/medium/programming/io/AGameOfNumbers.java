package hackeearth.medium.programming.io;

import java.util.Scanner;

public class AGameOfNumbers {

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
        for (int i = 0; i < arr.length - 2; i++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            boolean maxFound = false;
            for (int j = i + 1; j < arr.length; j++) {
                if(!maxFound && arr[j] > arr[i]) {
                    max = arr[j];
                    maxFound = true;
                }
                if(maxFound && arr[j] < max) {
                    min = arr[j];
                    break;
                }
            }
            if(min != Integer.MAX_VALUE) {
                System.out.print(min + " ");
            } else {
                System.out.print(-1 + " ");
            }
        }
        System.out.print(-1 + " " + -1);
    }

}
