package hackeearth.basic.programming.algo;

import java.util.Scanner;

public class CountingSort {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()];
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            arr[j] = scan.nextInt();
            max = Math.max(arr[j], max);
        }
        int[] aux = new int[max + 1];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = 0;
        }
        int[] sort = new int[arr.length];
        count_sort(arr, aux, sort);
        printAux(aux);
        scan.close();
    }

    private static void printAux(int[] aux) {
        for (int i = 0; i < aux.length; i++) {
            if(aux[i] > 0) {
                System.out.println(i + " " + aux[i]);
            }
        }
    }

    private static void count_sort(int[] arr, int[] aux, int[] sort) {
        for (int i = 0; i < arr.length; i++) {
            aux[arr[i]] += 1;
        }
        int j = 0;
        for (int i = 0; i < aux.length; i++) {
            int temp = aux[i];
            while (temp > 0) {
                sort[j++] = i;
                temp--;
            }
        }
    }

}