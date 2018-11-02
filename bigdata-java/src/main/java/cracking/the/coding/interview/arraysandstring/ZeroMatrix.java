package cracking.the.coding.interview.arraysandstring;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire
 * row and column are set to 0.
 */
public class ZeroMatrix {

	public static void main(String[] args) {
		int matrix[][] = { { 1, 2, 3 }, { 4, 0, 6 }, { 7, 8, 9 } };
		ZeroMatrix obj = new ZeroMatrix();

		obj.printMatrix(matrix);
		obj.zeroMatrix(matrix, matrix.length, matrix[0].length);
		obj.printMatrix(matrix);
	}

	private void zeroMatrix(int[][] matrix, int rows, int columns) {
		int[] rowArr = new int[rows];
		int[] columnArr = new int[columns];
		int rowIndex = 0, colIndex = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if(matrix[i][j] == 0) {
					rowArr[rowIndex++] = i;
					columnArr[colIndex++] = j;
				}
			}
		}
		for (int i = 0; i < rowIndex; i++) {
			zeroRows(matrix, rowArr[i], columns);
		}
		
		for (int i = 0; i < colIndex; i++) {
			zeroCols(matrix, columnArr[i], rows);
		}
	}
	
	private void zeroRows(int[][] matrix, int row, int cols) {
		for (int i = 0; i < cols; i++) {
			matrix[row][i] = 0;
		}
	}
	
	private void zeroCols(int[][] matrix, int col, int rows) {
		for (int i = 0; i < rows; i++) {
			matrix[i][col] = 0;
		}
	}
	
	private void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
