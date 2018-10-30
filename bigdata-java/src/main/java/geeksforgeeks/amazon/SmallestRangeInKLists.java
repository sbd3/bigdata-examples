package geeksforgeeks.amazon;

import java.util.Scanner;

public class SmallestRangeInKLists {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		while (t-- > 0) {
			int k = scan.nextInt(), n = scan.nextInt();
			int[][] arr = new int[k][n];
			for (int i = 0; i < k; i++)
				for (int j = 0; j < n; j++)
					arr[i][j] = scan.nextInt();
			findSmallestRange(arr, n, k);
		}
		scan.close();
	}

	private static void findSmallestRange(int[][] arr, int n, int k) {
		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			int counter = k;
			while(--counter >= 0) {
				//arr[counter][i];
			}
		}
	}

}
