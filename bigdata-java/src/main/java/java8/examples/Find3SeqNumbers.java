package java8.examples;

import java.util.Random;

public class Find3SeqNumbers {

    public static void main(String[] args) {
	int[] a = new int[10];
	Random rand = new Random(1000);
	for (int i = 0; i < 10; i++) {
	    a[i] = rand.nextInt(100);
	}
	for (int i : a) {
	    System.out.print(i +", ");
	}
	find3Numbers(a, a.length);
    }

    public static void find3Numbers(int[] a, int n) {
	if (a.length < 3) {
	    System.out.println("0");
	}

	int count = 0;
	for (int i = 0; i < a.length - 1; i++) {
	    if (a[i + 1] > a[i]) {
		count++;
	    } else if (a[i + 1] < a[i]) {
		count--;
	    }
	}
	if (count == a.length - 1) {
	    System.out.println("0");
	}

	int[] temp = new int[a.length];
	for (int i = 0; i < temp.length; i++) {
	    temp[i] = 0;
	}
	int max = a[0];
	int min = a[0];
	for (int i = 1; i < a.length; i++) {
	    if(a[i] > max && a[i] < min) {
		temp[i - 1]++;
		max = a[i];
	    } else {
		temp[i - 1]--;
		min = a[i];
	    }
	}
	System.out.println();
	for (int i = 0; i < temp.length; i++) {
	    System.out.print(temp[i] + ", ");
	}
	
    }
}
