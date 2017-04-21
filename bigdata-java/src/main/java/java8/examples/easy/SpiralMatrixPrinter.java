package java8.examples.easy;

import java.util.Scanner;

public class SpiralMatrixPrinter {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numOfTests = scan.nextInt();
		int[][] matrix = new int[4][4];
		for (int i = 0; i < numOfTests; i++) {
			for (int j = 0; j < 4; j++) {
				for (int j2 = 0; j2 < 4; j2++) {
					matrix[j][j2] = scan.nextInt();
				}
			}
			//processMatrix(matrix);
		}
		scan.close();
	}

	private static void processMatrix(int[][] matrix, int rowstart, int colstart, int rowend, int colend, int matrixlen) {
		for (int i = rowstart; i < matrixlen; i++) {
			for (int j = colstart; j < matrixlen; j++) {
				System.out.print(matrix[i][j]);
			}
		}
		
	}
	
	private static void printRow(int[][] matrix, int rowstart, int len) {
		
	}
	
	private static void printCol(int[][] matrix, int rowstart, int len) {
		
	}
}
