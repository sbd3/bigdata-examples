package java8.examples.easy;

import java.util.Scanner;

public class AddingOne {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testcases = scan.nextInt();
		for (int i = 0; i < testcases; i++) {
			int len = scan.nextInt();
			int[] arr = new int[len + 1];
			for (int j = 1; j < arr.length; j++) {
				arr[j] = scan.nextInt();
			}
			arr[0] = -1;
			
			int[] ret = add(arr, arr.length - 1, 1);
			
			if(arr[0] != 0) {
				System.out.print(arr[0] + " ");
			}
			for (int j = 1; j < ret.length; j++) {
				System.out.print(arr[j] + " ");
			}
			
			System.out.println();
		}
		scan.close();
	}

	private static int[] add(int[] arr, int index, final int num) {
		if(index == 0) {
			arr[0] = num;
			return arr;
		}
		if((arr[index] + num) <= 9) {
			arr[index] = arr[index] + num;
			add(arr, --index, 0);
		} else {
			arr[index] = 0;
			add(arr, --index, 1);
		}
		return arr;
	}
}
