package java8.examples.easy;

import java.util.Scanner;

public class ConsecutiveElements {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testcases = scan.nextInt();
		for (int i = 0; i < testcases; i++) {
			char[] arr = scan.next().toCharArray();
			deleteConsecutiveChars(arr);
		}
		scan.close();
	}

	private static void deleteConsecutiveChars(char[] arr) {
		char prev = arr[0];
		System.out.print(prev);
		for (int i = 1; i < arr.length; i++) {
			if(arr[i] != prev) {
				System.out.print(arr[i]);
			}
			prev = arr[i];
		}
		System.out.println();
	}
}
