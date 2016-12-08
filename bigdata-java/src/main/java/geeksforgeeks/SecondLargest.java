package geeksforgeeks;

import java.util.Scanner;

public class SecondLargest {

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	int n = scan.nextInt();
	for (int i = 0; i < n; i++) {
	    int[] arr = new int[scan.nextInt()];
	    for (int j = 0; j < arr.length; j++) {
		arr[j] = scan.nextInt();
	    }
	    findSecondMax(arr);
	}
	scan.close();
    }

    private static void findSecondMax(int[] arr) {
	int max = Integer.MIN_VALUE;
	int secondmax = Integer.MIN_VALUE;
	for (int i = 0; i < arr.length; i++) {
	    if(arr[i] > max) {
		secondmax = max;
		max = arr[i];
	    } else if(arr[i] > secondmax && arr[i] < max) {
		secondmax = arr[i];
	    }
	}
	System.out.println(secondmax);
    }
}
