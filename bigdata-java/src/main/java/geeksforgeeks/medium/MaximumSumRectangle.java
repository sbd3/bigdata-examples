package geeksforgeeks.medium;

import java.util.Scanner;

public class MaximumSumRectangle {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int[][] matrix = new int[scan.nextInt()][scan.nextInt()];
            for (int j = 0; j < matrix.length; j++) {
                for (int j2 = 0; j2 < matrix[0].length; j2++) {
                    matrix[j][j2] = scan.nextInt();
                }
            }
            printMatrix(matrix);
        }
        scan.close();
    }
    
    static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

}
