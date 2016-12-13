package geeksforgeeks.basic;

import java.util.Scanner;

public class PossibleGroups {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            findgroups(arr);
        }
        scan.close();
    }

    private static void findgroups(int arr[]) {
        // Create an array C[3] to store counts of elements with remainder
        // 0, 1 and 2.  c[i] would store count of elements with remainder i
        int c[] = new int[] { 0, 0, 0 };
        int i;

        int res = 0; // To store the result

        // Count elements with remainder 0, 1 and 2
        for (i = 0; i < arr.length; i++)
            c[arr[i] % 3]++;

        // Case 3.a: Count groups of size 2 from 0 remainder elements
        res += ((c[0] * (c[0] - 1)) >> 1);

        // Case 3.b: Count groups of size 2 with one element with 1
        // remainder and other with 2 remainder
        res += c[1] * c[2];

        // Case 4.a: Count groups of size 3 with all 0 remainder elements
        res += (c[0] * (c[0] - 1) * (c[0] - 2)) / 6;

        // Case 4.b: Count groups of size 3 with all 1 remainder elements
        res += (c[1] * (c[1] - 1) * (c[1] - 2)) / 6;

        // Case 4.c: Count groups of size 3 with all 2 remainder elements
        res += ((c[2] * (c[2] - 1) * (c[2] - 2)) / 6);

        // Case 4.c: Count groups of size 3 with different remainders
        res += c[0] * c[1] * c[2];

        // Return total count stored in res
        System.out.println(res);
    }

}
