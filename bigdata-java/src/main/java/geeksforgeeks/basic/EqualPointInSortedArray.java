package geeksforgeeks.basic;

import java.util.Scanner;

public class EqualPointInSortedArray {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int index = 0; index < num; index++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }

            if(arr.length == 1) {
                System.out.println("0");
                continue;
            }
            if(arr.length == 2) {
                System.out.println("-1");
                continue;
            }
            int i = 0, j = arr.length - 1;
            for (; i < j;) {
                if(arr[i] == arr[j]) {
                    break;
                } else if(arr[i] == arr[i + 1]) {
                    i++;
                } else if(arr[j] == arr[j - 1]) {
                    j--;
                } else {
                    i++;
                    j--;
                }
            }
            //System.out.println(i +":" + j);
            if(arr[i] == arr[j] || i == j) {
                System.out.println(i);
                continue;
            } else {
                System.out.println("-1");
                continue;
            }
        }
        scan.close();
    }

}
