package hackerrank.challenge;

public class FindNumber {

    public static void main(String[] args) {
        
    }
    
    static String findNumber(int[] arr, int k) {
        for (int i : arr) {
            if(i == k)
                return "YES";
        }
        return "NO";
    }

}
