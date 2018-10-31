package cracking.the.coding.interview.arraysandstring;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string
 * has sufficient space at the end to hold the additional characters, and that you are given the "true"
 * length of the string.
 */
public class URLify {

	public static void main(String[] args) {
		System.out.println(new URLify().urlify("http://www.google.com"));
		System.out.println(new URLify().urlify("http://www.google.com/page 1"));
		System.out.println(new URLify().urlify("Mr John Smith"));
		System.out.println(new URLify().urlify(null));
		System.out.println(new URLify().urlify(""));
	}
	
	private String urlify(String url) {
		if(url == null) {
			return null;
		}
		char[] arr = url.toCharArray();
		int spacesCount = 0;
		for (char c : arr) {
			if(c == ' ')
				spacesCount++;
		}
		char[] arr1 = new char[arr.length + 2 * spacesCount];
		int index = arr1.length - 1;
		for (int i = arr.length - 1; i >= 0; i--) {
			if(arr[i] == ' ') {
				arr1[index] = '0';
				arr1[index - 1] = '2';
				arr1[index - 2] = '%';
				index -= 3;
			} else {
				arr1[index] = arr[i];
				index--;
			}
		}
		return String.valueOf(arr1);
	}

}
