package cracking.the.coding.interview.arraysandstring;

import java.util.Arrays;

/**
 * Given two strings, write a method to decide if one is a permutation of the other
 */
public class CheckPermutations {

	public static void main(String[] args) {
		System.out.println(new CheckPermutations().checkPermutations("abc", "cab"));
		System.out.println(new CheckPermutations().checkPermutations1("abd", "dab"));
	}
	
	/**
	 * Using sorting
	 * @param str1
	 * @param str2
	 * @return
	 */
	private boolean checkPermutations(String str1, String str2) {
		if(str1.length() != str2.length()) return false;
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		return String.valueOf(arr1).equals(String.valueOf(arr2));
	}
	
	/**
	 * Using character count
	 * @param str1
	 * @param str2
	 * @return
	 */
	private boolean checkPermutations1(String str1, String str2) {
		if(str1.length() != str2.length()) return false;
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		
		// Due to character ASCII encoding
		int[] charsCount = new int[128];
		for (int i = 0; i < charsCount.length; i++) {
			charsCount[i] = 0;
		}
		for (char i : arr1) {
			charsCount[i]++;
		}
		for (char i : arr2) {
			charsCount[i]--;
			if(charsCount[i] < 0) {
				return false;
			}
		}
		return true;
	}

}
