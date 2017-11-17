package hackeearth.medium.programming.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MagicalWords {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int noOfTests = s.nextInt();

        // 65 - 90, 97 - 122
        HashMap<Integer, Integer> valuesMap = processList();
        System.out.println(valuesMap);
        for (int i = 0; i < noOfTests; i++) {
            s.nextInt();
            char[] arr = s.next().toCharArray();
            for (char c : arr) {
                System.out.print((char) valuesMap.get((int) c).intValue());
            }
            System.out.println();
        }

        s.close();
    }

    private static HashMap<Integer, Integer> processList() {
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 65; i <= 90; i++) {
            if (isPrime(i)) {
                arr.add(i);
            }
        }
        for (int i = 97; i <= 122; i++) {
            if (isPrime(i)) {
                arr.add(i);
            }
        }
        HashMap<Integer, Integer> valuesMap = new HashMap<>();
        for (int i = 65, j = 0; i <= 90 && j < arr.size() - 1;) {
            if(Math.abs(arr.get(j) - i) > Math.abs(arr.get(j + 1) - i)) {
                valuesMap.put(i, arr.get(j + 1));
                j++;
            } else {
                valuesMap.put(i, arr.get(j));
                i++;
            }
        }
        for (int i = 97, j = 0; i <= 122 && j < arr.size() - 1;) {
            if(Math.abs(arr.get(j) - i) > Math.abs(arr.get(j + 1) - i)) {
                valuesMap.put(i, arr.get(j + 1));
                j++;
            } else {
                valuesMap.put(i, arr.get(j));
                i++;
            }
        }
        if(valuesMap.size() < 52) {
            for (int i = 113; i <= 122; i++) {
                valuesMap.put(i, arr.get(arr.size()-1));
            }
        }
        return valuesMap;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

}
