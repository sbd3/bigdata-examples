package geeksforgeeks.basic;

import java.util.Scanner;

public class ShortestDirection {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            String str = scan.next();
            findShortestDirection(str);
        }
        scan.close();
    }

    private static void findShortestDirection(String str) {
        char[] arr = str.toCharArray();
        int e = 0, w = 0, n = 0, s = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 'E') {
                e++;
            } else if(arr[i] == 'W') {
                w++;
            } else if(arr[i] == 'N') {
                n++;
            } else if(arr[i] == 'S') {
                s++;
            }
        }
        int min = Math.min(e, w);
        e -= min;
        w -= min;
        min = Math.min(n, s);
        n -= min;
        s -= min;
        if(e > 0) {
            printStr('E', e);
        }
        if(n > 0) {
            printStr('N', n);
        }
        if(s > 0) {
            printStr('S', s);
        }
        if(w > 0) {
            printStr('W', w);
        }
        System.out.println();
    }
    
    private static void printStr(char c, int e) {
        for (int i = 0; i < e; i++) {
            System.out.print(c);
        }
    }

}
