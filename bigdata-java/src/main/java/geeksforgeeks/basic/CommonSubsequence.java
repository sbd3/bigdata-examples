package geeksforgeeks.basic;

import java.util.Scanner;

public class CommonSubsequence {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numOfTests = scan.nextInt();
        for (int i = 0; i < numOfTests; i++) {
            String a = scan.next();
            String b = scan.next();
            processStr(a.toCharArray(), b.toCharArray());
        }
        scan.close();
    }

    private static void processStr(char[] cs, char[] cs2) {
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < cs2.length; j++) {
                if(cs[i] == cs2[j]) {
                    count++;
                    j = cs2.length;
                    i = cs.length;
                }
            }
        }
        if(count == 0) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
    }

}
