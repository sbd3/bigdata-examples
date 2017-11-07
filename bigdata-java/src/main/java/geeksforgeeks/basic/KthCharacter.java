package geeksforgeeks.basic;

import java.util.Scanner;

public class KthCharacter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int num = scan.nextInt();
            int pos = scan.nextInt();
            int iter = scan.nextInt();
            Double div = pos / Math.pow(2, iter);
            Double mod = pos % Math.pow(2, iter);
            char bit = Integer.toBinaryString(num).toCharArray()[div.intValue()];
            String str = String.valueOf(bit);
            StringBuilder sb = new StringBuilder(bit);
            for (int j = 0; j < iter; j++) {
                for (char ch : str.toCharArray()) {
                    if(ch == '0') {
                        sb.append("01");
                    } else {
                        sb.append("10");
                    }
                }
                str = sb.toString();
                sb = new StringBuilder();
            }
            System.out.println(str.charAt(mod.intValue()));
        }
        scan.close();
    }
}
