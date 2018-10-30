package geeksforgeeks.amazon;

import java.util.Scanner;

public class ClosestPalindrome {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCases = scan.nextInt();
		while (testCases-- > 0) {
			int val = scan.nextInt();
			process(val);
		}
		scan.close();
	}

	private static void process(int val) {
		if (isPalindrome(String.valueOf(val).toCharArray())) {
			System.out.println(val);
			return;
		}

	}

	private static boolean isPalindrome(char[] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			if (arr[arr.length - 1 - i] != arr[i]) {
				return false;
			}
		}
		return true;
	}

}
