package geeksforgeeks.basic;

import java.util.List;
import java.util.Scanner;

public class RatMaze {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int noOfTests = scan.nextInt();
        for (int i = 0; i < noOfTests; i++) {
            int size = scan.nextInt();
            int[][] matrix = new int[size][size];
            for (int j = 0; j < matrix.length; j++) {
                for (int j2 = 0; j2 < matrix.length; j2++) {
                    matrix[j][j2] = scan.nextInt();
                }
            }
            printPath(matrix, size);
        }
        scan.close();
    }
    
    private static List<String> printPath(int[][] matrix, int n) {
        return null;
    }
    
}
