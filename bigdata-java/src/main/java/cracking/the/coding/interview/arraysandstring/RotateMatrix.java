package cracking.the.coding.interview.arraysandstring;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is
 * 4 bytes. Write a method to rotate the image by 90 degrees. Can you do this in
 * place?
 */
public class RotateMatrix {

	public static void main(String[] args) {
		int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		RotateMatrix obj = new RotateMatrix();

		obj.printMatrix(matrix);
		obj.rotateMatrix(matrix);
		obj.printMatrix(matrix);
	}

	private void rotateMatrix(int[][] matrix) {
		
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
