package geeksforgeeks.medium;

import java.util.LinkedList;
import java.util.Scanner;

public class SegregateEvenOddNodes {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int num = scan.nextInt();
            LinkedList<Integer> list = new LinkedList<>();
            for (int j = 0; j < num; j++) {
                list.add(scan.nextInt());
            }
            LinkedList<Integer> ll = processList(list);
            for (Integer in : ll) {
                System.out.print(in + " ");
            }
            System.out.println();
        }
        scan.close();
    }

    private static LinkedList<Integer> processList(LinkedList<Integer> list) {
        LinkedList<Integer> even = new LinkedList<>();
        LinkedList<Integer> odd = new LinkedList<>();
        for (Integer i : list) {
            if(i % 2 == 0) {
                even.add(i);
            } else {
                odd.add(i);
            }
        }
        even.addAll(odd);
        return even;
    }

}
