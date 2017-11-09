package geeksforgeeks.basic;

import java.util.LinkedList;
import java.util.Scanner;

public class PositiveNegativeElements {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int len = scan.nextInt();
            LinkedList<Integer> positive = new LinkedList<>();
            LinkedList<Integer> negative = new LinkedList<>();
            for (int j = 0; j < len; j++) {
                int val = scan.nextInt();
                if(val >= 0) {
                    positive.add(val);
                } else {
                    negative.add(val);
                }
                while(!positive.isEmpty() && !negative.isEmpty()) {
                    System.out.print(positive.pop() + " " + negative.pop() + " ");
                }
            }
            System.out.println();
        }
        scan.close();
    }
    
}
