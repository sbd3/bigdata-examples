package geeksforgeeks.basic;

import java.util.Scanner;

public class RotateBy90Degree {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            int n = scan.nextInt();
            int[][] matrix = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    matrix[j][k] = scan.nextInt();
                }
            }
            printmatrix(matrix);
            printTranspose(matrix);
            printmatrix(matrix);
            /*
             0,0 0,1 0,2    0,2 1,2 2,2
             1,0 1,1 1,2    0,1 1,1 2,1
             2,0 2,1 2,2    0,0 1,0 2,0
             */
        }
        scan.close();
    }

    private static void printmatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void printTranspose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                System.out.println("Swapping: {" + i + "," + j + "} : {" + j + "," + (matrix.length - 1 - i) + "}");
                swap(matrix, i, j, j, matrix.length - 1 - i);
            }
        }
    }

    private static void swap(int[][] matrix, int x, int y, int x1, int y1) {
        int temp = matrix[x][y];
        matrix[x][y] = matrix[x1][y1];
        matrix[x1][y1] = temp;
        printmatrix(matrix);
    }

}
