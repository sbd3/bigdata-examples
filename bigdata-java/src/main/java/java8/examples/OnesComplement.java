package java8.examples;

public class OnesComplement {

    public static void main(String[] args) {
        int val = -4;
        int minVal = Integer.MIN_VALUE;
        int maxVal = Integer.MAX_VALUE;
        
        System.out.println("Min Value: " + minVal + ". Binary: " + Integer.toBinaryString(minVal) + ". Negate: " + ~minVal);
        System.out.println("Value: " + val + ". Binary: " + Integer.toBinaryString(val) + ". Negate: " + ~val);
        System.out.println("Min Value: " + maxVal + ". Binary: " + Integer.toBinaryString(maxVal) + ". Negate: " + ~maxVal);
        
        System.out.println(Integer.numberOfLeadingZeros(val));
    }
}
