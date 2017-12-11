package geeksforgeeks.basic;

import java.util.ArrayList;
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
            for(String path: printPath(matrix, size)) {
                System.out.println(path);
            }
        }
        scan.close();
    }

    private static ArrayList<String> printPath(int[][] matrix, int n) {
        ArrayList<String> paths = new ArrayList<>();
        process(matrix, n, 0, 0, "", paths);
        return paths;
    }

    private static void process(int[][] matrix, int n, int i, int j, String path, ArrayList<String> paths) {
        if (i == n - 1 && j == n - 1) {
            paths.add(path);
        }
        if(i < n - 1 && matrix[i + 1][j] == 1) {
            process(matrix, n, i + 1, j, path + "D", paths);
        }
        if(j < n - 1 && matrix[i][j + 1] == 1) {
            process(matrix, n, i, j + 1, path + "R", paths);
        }
    }

}
