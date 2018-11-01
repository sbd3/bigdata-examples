package cracking.the.coding.interview.arraysandstring;

/**
 * There are three types of edits that can be performed on strings: 
 * insert a character, remove a character, or replace a character. 
 * Given two strings, write a function to check if they are one edit (or zero edits) away.
 */
public class OneAway {

	public static void main(String[] args) {
		System.out.println(new OneAway().oneAway("pale".toCharArray(), "ple".toCharArray()));
		System.out.println(new OneAway().oneAway("pales".toCharArray(), "pale".toCharArray()));
		System.out.println(new OneAway().oneAway("pale".toCharArray(), "bale".toCharArray()));
		System.out.println(new OneAway().oneAway("pale".toCharArray(), "bake".toCharArray()));
	}
	
	private boolean oneAway(char[] str1, char[] str2) {
		if(str1.length == str2.length)
			return isReplace(str1, str2);
		else if(str1.length - 1 == str2.length)
			return isInsert(str1, str2);
		else if(str1.length == str2.length - 1)
			return isDelete(str1, str2);
		return false;
	}
	
	private boolean isInsert(char[] str1, char[] str2) {
		return isDelete(str2, str1);
	}
	
	private boolean isDelete(char[] str1, char[] str2) {
		int count = 0;
		for (int i = 0, j = 0; i < str1.length && j < str2.length; i++, j++) {
			if(str1[i] != str2[j]) {
				i++;
				count++;
			}
			if(count > 1)
				return false;
		}
		return true;
	}
	
	private boolean isReplace(char[] str1, char[] str2) {
		boolean foundDifference = false;
		for (int i = 0; i < str1.length; i++) {
			if(str1[i] != str2[i]) {
				if(foundDifference)
					return false;
				foundDifference = true;
			}
		}
		return true;
	}
	

}
