package geeksforgeeks.basic;

import java.util.Scanner;

public class MinimizeHeights {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numTests = scan.nextInt();
		for (int i = 0; i < numTests; i++) {
			int k = scan.nextInt();
			int[] arr = new int[scan.nextInt()];
			for (int j = 0; j < arr.length; j++) {
				arr[j] = scan.nextInt();
			}
			System.out.println(printMinHeight(k, arr));
		}
		scan.close();
	}

	private static int printMinHeight(int k, int[] arr) {
		if(arr.length == 0) {
			return 0;
		}
		if(arr.length == 1) {
			return arr[0];
		}
		if (arr[0] > arr[1]) {
			arr[0] -= k;
		} else {
			arr[0] += k;
		} 
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				arr[i + 1] += k;
			} else {
				arr[i + 1] -= k;
			}
		}
		return getDiffHeight(arr);
	}
	
	private static int getDiffHeight(int[] arr) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max - min;
	}

}
