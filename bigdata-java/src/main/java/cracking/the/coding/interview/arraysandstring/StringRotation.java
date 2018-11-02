package cracking.the.coding.interview.arraysandstring;

/**
 * Given two strings, 51 and 52, write code to check if 52 is a rotation of 51
 * using only one call to i5Sub5tring (e.g., "waterbottle" is a rotation
 * of"erbottlewat")
 */
public class StringRotation {

	public static void main(String[] args) {
		System.out.println(new StringRotation().isStringRotation("waterbottle", "erbottlewat"));
	}
	
	private boolean isStringRotation(String str1, String str2) {
		return (str2 + str2).contains(str1);
	}

}
