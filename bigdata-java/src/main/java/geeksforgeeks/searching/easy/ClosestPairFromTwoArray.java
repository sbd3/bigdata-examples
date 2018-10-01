package geeksforgeeks.searching.easy;

import java.util.Scanner;

public class ClosestPairFromTwoArray {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCases = scan.nextInt();
		for (int i = 0; i < testCases; i++) {
			int[] arrA = new int[scan.nextInt()];
			int[] arrB = new int[scan.nextInt()];
			for (int j = 0; j < arrA.length; j++) {
				arrA[j] = scan.nextInt();
			}
			for (int j = 0; j < arrB.length; j++) {
				arrB[j] = scan.nextInt();
			}
			int sum = scan.nextInt();
			process(arrA, arrB, sum);
		}
		scan.close();
	}

	private static void process(int[] arrA, int[] arrB, int sum) {
		int l = arrA[0], r = arrB[0];
		for (int i = 0; i < arrA.length; i++) {
			for (int j = 0; j < arrB.length; j++) {
				if (Math.abs(sum - (arrA[i] + arrB[j])) < Math.abs(sum - (l + r))) {
					l = arrA[i];
					r = arrB[j];
				} else if((Math.abs(sum - (arrA[i] + arrB[j])) == Math.abs(sum - (l + r))) && arrA[i] < l) {
					l = arrA[i];
					r = arrB[j];
				}
			}
		}
		System.out.println(l + " " + r);
	}
}
