package geeksforgeeks.basic;

import java.util.Scanner;

public class MedianStream {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] arr = new int[n];
		for (int i = 1; i <= n; i++) {
			arr[i - 1] = scan.nextInt();
			double index = 0.5 * (i + 1);
			int lower = (int) Math.floor(index);
			int upper = (int) Math.ceil(index);
			double remainder = Math.ceil(index) - index;
			System.out.println(arr[lower - 1] + ((arr[upper - 1]-arr[lower - 1]) * remainder));
		}
		scan.close();
	}

}
