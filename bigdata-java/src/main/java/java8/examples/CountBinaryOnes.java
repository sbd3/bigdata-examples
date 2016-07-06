package java8.examples;

public class CountBinaryOnes {

    public static void main(String[] args) {
        System.out.println(countOnes(Integer.MAX_VALUE));
    }
    
    private static int countOnes(int num) {
        int count = 0;
        while(num > 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }
}
