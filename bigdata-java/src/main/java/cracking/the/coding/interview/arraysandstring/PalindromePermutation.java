package cracking.the.coding.interview.arraysandstring;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome. 
 * A palindrome is a word or phrase that is the same forwards and backwards. 
 * A permutation is a rearrangement of letters.
 */
public class PalindromePermutation {

	public static void main(String[] args) {
		System.out.println(new PalindromePermutation().palindromePermutation("Taact Ca"));
	}
	
	private boolean palindromePermutation(String str) {
		HashMap<Character, Integer> countMap = new HashMap<>();
		for (char c : str.toLowerCase().toCharArray()) {
			if(c == ' ') continue;
			if(countMap.containsKey(c))
				countMap.put(c, countMap.get(c) + 1);
			else
				countMap.put(c, 1);
		}
		int count = 0;
		for (Entry<Character, Integer> entry : countMap.entrySet()) {
			if(entry.getValue() % 2 != 0)
				count++;
			if(count > 1)
				return false;
		}
		return true;
	}
	

}
