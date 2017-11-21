package hackerrank.challenge;

public class DiscountedPrice {

    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 3;
        arr[2] = 3;
        arr[3] = 2;
        arr[4] = 5;
        finalPrice(arr);
    }
    
    // 1 3 3 2 5
    static void finalPrice(int[] prices) {
        int sum = prices[prices.length - 1];
        int min = prices[prices.length - 1];
        String str = String.valueOf(prices.length - 1);
        for (int i = prices.length - 2; i >= 0; i--) {
            if(prices[i] < min) {
                sum += prices[i];
                min = prices[i];
                str = i + " " + str;
            } else {
                sum += prices[i] - min;
            }
        }
        System.out.println(sum);
        System.out.println(str);
    }

}
