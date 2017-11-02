package geeksforgeeks.basic;

public class Solution {

    public static void main(String[] args) {
        int[] arr = new int[7];
        arr[0] = 2;
        arr[1] = 2;
        arr[2] = 2;
        arr[3] = 2;
        arr[4] = 2;
        arr[5] = 2;
        arr[6] = 2;
        System.out.println(oldSolution(arr));
    }
    
    static int oldSolution(int[] A) {
        int N = A.length;
        for (int i = 0; i < N; i++)
            for (int j = N - 1; j > i; j--)
                if (A[i] == A[j]) {
                    return Math.abs(i - j);
                }
        return -1;
    }

    static int solution(int[] A) {
        int N = A.length;
        int result = 0;
        for (int i = 0, j = N - 1; i < N && j > 0; i++, j--)
           if (A[i] == A[j]) {
               result = Math.max(result, Math.abs(i - j));
           }
        return result;
    }
}
