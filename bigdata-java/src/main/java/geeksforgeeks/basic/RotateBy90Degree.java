package geeksforgeeks.basic;

import java.util.Scanner;

public class RotateBy90Degree {

	// TO DO
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
			rotateMatrix(matrix, n);
			printmatrix(matrix);
		}
		scan.close();
	}

	private static void printmatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void rotateMatrix(int[][] mat, int n) {
		// Consider all squares one by one
		for (int x = 0; x < n / 2; x++) {
			// Consider elements in group of 4 in
			// current square
			for (int y = x; y < n - x - 1; y++) {
				// store current cell in temp variable
				int temp = mat[x][y];

				// move values from right to top
				mat[x][y] = mat[y][n - 1 - x];

				// move values from bottom to right
				mat[y][n - 1 - x] = mat[n - 1 - x][n - 1 - y];

				// move values from left to bottom
				mat[n - 1 - x][n - 1 - y] = mat[n - 1 - y][x];

				// assign temp to left
				mat[n - 1 - y][x] = temp;
			}
		}
	}

}
