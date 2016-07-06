package java8.examples;

public class BitwiseOddEven {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(checkEven(i));
        }
    }
    
    private static boolean checkEven(int num) {
         return (Integer.lowestOneBit(num) & 1) == 1 ? false : true;
    }
}
