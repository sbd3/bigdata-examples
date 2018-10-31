package cracking.the.coding.interview.arraysandstring;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
 * @author Puneet Singh
 */
public class IsUnique {

	public static void main(String[] args) {
		System.out.println(new IsUnique().isUnique("abcdefghijkl"));
		System.out.println(new IsUnique().isUnique("abcdefgghijkl"));
		System.out.println(new IsUnique().isUnique("abcdefghijkll"));
		System.out.println(new IsUnique().isUnique("aabcdefghijkl"));
		
		
		System.out.println(new IsUnique().isUnique1("abcdefghijkl"));
		System.out.println(new IsUnique().isUnique1("abcdefgghijkl"));
		System.out.println(new IsUnique().isUnique1("abcdefghijkll"));
		System.out.println(new IsUnique().isUnique1("aabcdefghijkl"));
	}
	
	/**
	 * Using the char array sorting
	 * @param str
	 * @return
	 */
	private boolean isUnique(String str) {
		if(str == null || str.isEmpty() || str.length() > 128) {
			return false;
		}
		char[] arr = str.toCharArray();
		Arrays.sort(arr);
		for (int i = 0; i < arr.length - 1; i++) {
			if(arr[i] == arr[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Using Hashset for caching
	 * @param str
	 * @return
	 */
	private boolean isUnique1(String str) {
		if(str == null || str.isEmpty() || str.length() > 128) {
			return false;
		}
		HashSet<Character> countMap = new HashSet<>();
		for (char c : str.toCharArray()) {
			if(countMap.contains(c))
				return false;
			countMap.add(c);
		}
		return true;
	}

}
