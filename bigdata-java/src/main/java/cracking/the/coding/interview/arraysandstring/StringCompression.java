package cracking.the.coding.interview.arraysandstring;

/**
 *  Implement a method to perform basic string compression using the counts of repeated characters. 
 *  For example, the string aabcccccaaa would become a2blc5a3.
 */
public class StringCompression {

	public static void main(String[] args) {
		System.out.println(new StringCompression().compressString("aabcccccaaa".toCharArray()));
		System.out.println(new StringCompression().compressString("abcd".toCharArray()));
		// this fails
		System.out.println(new StringCompression().compressString("abbbcccd".toCharArray()));
	}
	
	private String compressString(char[] str) {
		StringBuilder sb = new StringBuilder(str.length);
		int count = 0;
		boolean isCompressed = false;
		for (int i = 0; i < str.length; i++) {
			count++;
			if(count > 1)
				isCompressed = true;
			if(i + 1 >= str.length || str[i + 1] != str[i]) {
				sb.append(str[i]).append(count);
				count = 0;
			}
		}
		return isCompressed && sb.length() <= str.length ? sb.toString() : String.valueOf(str);
	}

}
