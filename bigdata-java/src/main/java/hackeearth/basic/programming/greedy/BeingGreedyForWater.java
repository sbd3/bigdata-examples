package hackeearth.basic.programming.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BeingGreedyForWater {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int[] arr = new int[scan.nextInt()];
            long capacity = scan.nextLong();
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            Arrays.sort(arr);
            int count = 0;
            long sum  = 0;
            for (int j = 0; j < arr.length; j++) {
                sum += arr[j];
                if(sum < capacity) {
                    count++;
                } else {
                    break;
                }
            }
            System.out.println(count);
        }
        scan.close();
    }

}
