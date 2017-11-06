package geeksforgeeks.basic;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class PositiveNegativePairs {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int[] arr = new int[scan.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scan.nextInt();
            }
            processArr(arr);
        }
        scan.close();
    }

    private static void processArr(int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            int absVal = Math.abs(arr[i]);
            if(map.containsKey(absVal)) {
                map.put(absVal, map.get(absVal) + 1);
            } else {
                map.put(absVal, 1);
            }
        }
        int count = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > 1) {
                count++;
                System.out.print(-entry.getKey() + " " + entry.getKey() + " ");
            }
        }
        if(count == 0)
            System.out.println(0);
        else
            System.out.println();
    }
}
