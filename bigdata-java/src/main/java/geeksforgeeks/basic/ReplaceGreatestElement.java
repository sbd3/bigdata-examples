package geeksforgeeks.basic;

public class ReplaceGreatestElement {

    public static void main(String[] args) {
        int[] arr = new int[6];
        arr[0] = 16;
        arr[1] = 17;
        arr[2] = 4;
        arr[3] = 3;
        arr[4] = 5;
        arr[5] = 2;
        printArr(arr);
        arr = replaceElements(arr);
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static int[] replaceElements(int[] arr) {
        int max = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            max = Math.max(max, temp);
        }
        return arr;
    }
}
