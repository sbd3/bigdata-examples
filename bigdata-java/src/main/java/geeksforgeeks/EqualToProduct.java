package geeksforgeeks;

import java.util.Scanner;

public class EqualToProduct {

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	int n = scan.nextInt();
	for (int i = 0; i < n; i++) {
	    int[] arr = new int[scan.nextInt()];
	    int num = scan.nextInt();
	    for (int j = 0; j < arr.length; j++) {
		arr[j] = scan.nextInt();
	    }
	    bruteForce(arr, num);
	    //findEqualToProductPair(arr, findDivisors(num), num);
	}
	scan.close();
    }
    
    private static void bruteForce(int[] arr, int num) {
	boolean flag = false;
	for (int i = 0; i < arr.length; i++) {
	    for (int j = 1; j < arr.length; j++) {
		if(arr[i] * arr[j] == num) {
		    flag = true;
		}
	    }
	}
	if(flag) 
	    System.out.println("Yes");
	else
	    System.out.println("No");
    }

    private static void findEqualToProductPair(int[] arr, int[] divisors, int num) {
	
    }

    private static int[] findDivisors(int n) {
	int[] primeNo = new int[n / 2];
	for (int i = 0; i < primeNo.length; i++) {
	    primeNo[i] = -1;
	}
	for (int i = 1, j = 0; i <= n / 2; i++) {
	    if (n % i == 0 && (isPrime(i) || i == n)) {
		primeNo[j++] = i;
	    }
	}
	return primeNo;
    }

    private static boolean isPrime(int n) {
	boolean flag = false;
	for (int i = 1; i <= n / 2; i++) {
	    if (n % i == 0) {
		flag = true;
	    }
	}
	return flag;
    }
}
