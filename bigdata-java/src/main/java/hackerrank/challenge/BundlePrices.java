package hackerrank.challenge;

import java.util.Arrays;

public class BundlePrices {
    
    public static void main(String[] args) {
        int[] bundleQuantities = new int[1];
        int[] bundleCosts = new int[1];
        bundleQuantities[0] = 10;
        bundleCosts[0] = 2;
        
        System.out.println(budgetShopping(4, bundleQuantities, bundleCosts));
    }

    static int budgetShopping(int n, int[] bundleQuantities, int[] bundleCosts) {
        class Bundle implements Comparable<Bundle> {
            int quantity;
            int price;
            public Bundle(int quantity, int price) {
                this.quantity = quantity;
                this.price = price;
            }
            @Override
            public int compareTo(Bundle o) {
                double perPriceThis = quantity / (double) price;
                double perPriceThat = o.quantity / (double) o.price;
                if(perPriceThis > perPriceThat) {
                    return 1;
                } else if(perPriceThis == perPriceThat) {
                    return 0;
                } else {
                    return -1;
                }
            }
            
        }
        Bundle[] arr = new Bundle[bundleCosts.length];
        for (int i = 0; i < bundleCosts.length; i++) {
           Bundle bundle = new Bundle(bundleQuantities[i], bundleCosts[i]);
           arr[i] = bundle;
        }
        Arrays.sort(arr);
        int num = 0;
        while(n >= arr[0].price) {
            num += arr[0].quantity;
            n -= arr[0].price;
        }
        return num;
    }
}
