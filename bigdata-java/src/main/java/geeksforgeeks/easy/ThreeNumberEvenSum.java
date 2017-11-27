package geeksforgeeks.easy;

import java.util.Scanner;

public class ThreeNumberEvenSum {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int val = scan.nextInt();
            long oddCount = val / 2 + val % 2, evenCount = val / 2;
            long oCount = (oddCount * (oddCount  - 1) * evenCount) / 2;
            long eCount = evenCount * (evenCount - 1) * (evenCount - 2) / 6;
            System.out.println((oCount + eCount) % 1000000007);
        }
        scan.close();
    }
}
