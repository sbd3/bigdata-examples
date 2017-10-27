package geeksforgeeks.basic;

import java.util.Scanner;

public class OverlappingRectangle {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nTestCases = scan.nextInt();
        for (int i = 0; i < nTestCases; i++) {
            int l1x = scan.nextInt();
            int l1y = scan.nextInt();
            int r1x = scan.nextInt();
            int r1y = scan.nextInt();
            
            int l2x = scan.nextInt();
            int l2y = scan.nextInt();
            int r2x = scan.nextInt();
            int r2y = scan.nextInt();
            
            if(l1x > r2x || r1x < l2x) {
                System.out.println(0);
            } else if(r1y > l2y || l1y < r2y) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
        }
        scan.close();
    }
    
}
