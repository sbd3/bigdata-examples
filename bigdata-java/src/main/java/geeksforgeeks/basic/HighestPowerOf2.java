package geeksforgeeks.basic;

public class HighestPowerOf2 {

    public static void main(String[] args) {
        printHighestPowerOf2(32);
    }
    
    static void printHighestPowerOf2(int num) {
        int count = 0;
        while(num > 1) {
            num = num / 2;
            count++;
        }
        System.out.println(Math.pow(2, count));
    }
}
