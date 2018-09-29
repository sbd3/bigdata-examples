package geeksforgeeks.searching.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/smallest-greater-elements-in-whole-array/0
 * 
 * @author Puneet Singh
 */
public class SmallestGreaterElementsInArray {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCases = scan.nextInt();
		for (int i = 0; i < testCases; i++) {
			int[] arr = new int[scan.nextInt()];
			for (int j = 0; j < arr.length; j++) {
				arr[j] = scan.nextInt();
			}
			process(arr);
		}
		scan.close();
	}

	private static void process(int[] arr) {
		int[] res = arr.clone();
		Arrays.sort(arr);
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length - 1; i++) {
			map.put(arr[i], arr[i + 1]);
		}
		for (int i = 0; i < res.length; i++) {
			if(res[i] != arr[arr.length - 1])
				System.out.print(map.get(res[i]) + " ");
			else
				System.out.print("_ ");
		}
		System.out.println();
	}

}
