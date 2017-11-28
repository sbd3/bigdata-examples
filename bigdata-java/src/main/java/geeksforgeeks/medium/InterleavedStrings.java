package geeksforgeeks.medium;

import java.util.Scanner;

public class InterleavedStrings {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            String a = scan.next();
            String b = scan.next();
            String c = scan.next();
            System.out.println(isInterLeave(a, b, c));
        }
        scan.close();
    }
    
    public static boolean isInterLeave(String a,String b,String c)
    {
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        char[] cArr = c.toCharArray();

        int j = 0, aIndex = 0, bIndex = 0;
        for (; j < cArr.length; j++) {
            if (aIndex < aArr.length && cArr[j] == aArr[aIndex]) {
                aIndex++;
            } 
            if (bIndex < bArr.length && cArr[j] == bArr[bIndex]) {
                bIndex++;
            }
        }
        if (aIndex == aArr.length && bIndex == bArr.length) {
            return true;
        } else {
            return false;
        }
    }
}
