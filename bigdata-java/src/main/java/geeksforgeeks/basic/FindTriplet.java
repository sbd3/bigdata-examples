package geeksforgeeks.basic;

import java.util.HashSet;
import java.util.Scanner;

public class FindTriplet {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            System.out.println(new FindTriplet().findTriplets(arr, arr.length));
        }
        scan.close();
    }

    public int findTriplets(int arr[], int n) {
        for (int i = 0; i < n - 1; i++) {
            HashSet<Integer> s = new HashSet<Integer>();
            for (int j = i + 1; j < n; j++) {
                if (s.contains(-(arr[i] + arr[j]))) {
                    return 1;
                } else
                    s.add(arr[j]);
                System.out.println(s);
            }
        }
        return 0;
    }
}
