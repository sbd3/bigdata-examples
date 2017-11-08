package ds.algo.madeeasy;

public class PrintAllStrings {
    static int[] arr;
    public static void main(String[] args) {
        arr = new int[3];
        binary(3);
    }
    
    static void binary(int n) {
        if(n < 1) {
            printArr(arr);
        } else {
            arr[n-1] = 0;
            binary(n-1);
            arr[n-1] = 1;
            binary(n-1);
        }
    }
    
    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
