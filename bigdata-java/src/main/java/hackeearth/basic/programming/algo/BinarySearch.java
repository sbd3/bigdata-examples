package hackeearth.basic.programming.algo;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);
        int q = scan.nextInt();
        for (int i = 0; i < q; i++) {
            int num = scan.nextInt();
            int res = binarySearch(arr, num); 
            if(res == -1) {
                System.out.println(res);
            } else {
                System.out.println(res + 1);
            }
        }
        scan.close();
    }

    private static int binarySearch(int[] arr, int num) {
        int lo = 0, high = arr.length - 1;
        while (lo <= high) {
            int mid = (lo + high) / 2;
            if (arr[mid] > num) {
                high = mid - 1;
            } else if (arr[mid] < num) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
