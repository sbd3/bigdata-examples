package geeksforgeeks.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class FirstNonRepeatingCharacter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int streamSize = scan.nextInt();
            LinkedList<String> vals = new LinkedList<>();
            HashSet<String> valSet = new HashSet<>(); 
            for (int j = 0; j < streamSize; j++) {
                String val = scan.next();
                if(vals.contains(val)) {
                    vals.remove(val);
                    valSet.add(val);
                } else {
                    if(!valSet.contains(val)) {
                        vals.add(val);
                    }
                }
                if(vals.isEmpty()) {
                    System.out.print("-1" + " ");
                } else {
                    System.out.print(vals.getFirst() + " ");
                }
            }
            System.out.println();
        }
        scan.close();
    }

}
