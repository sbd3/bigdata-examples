package java8.examples;

public class StringToBinary {
	
	public static void main(String[] args) {
		System.out.println(stringToBinary("Hello", true));
	}

	public static String stringToBinary(String str, boolean pad ) {
	    byte[] bytes = str.getBytes();
	    StringBuilder binary = new StringBuilder();
	    for (byte b : bytes)
	    {
	       binary.append(Integer.toBinaryString((int) b));
	       if(pad) { binary.append(' '); }
	    }
	    return binary.toString();        
	}
}
