package java8.examples.easy;

import java.util.Scanner;

public class TransitionPoint {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numTests = scan.nextInt();
		for (int i = 0; i < numTests; i++) {
			int[] arr = new int[scan.nextInt()];
			for (int j = 0; j < arr.length; j++) {
				arr[j] = scan.nextInt();
			}
			process(arr, arr.length);
		}
		scan.close();
	}

	private static void process(int[] arr, int size) {
		for (int i = 0; i < arr.length - 1; i++) {
			if(arr[i] != arr[i+1]) {
				System.out.println(i+1);
				return;
			}
		}
	}
}
