package hackeearth.basic.programming.greedy;

import java.util.Scanner;

public class EdgeExistence {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nodes = scan.nextInt();
        int[][] nodesMatrix = new int[nodes][nodes];
        for (int i = 0; i < nodesMatrix.length; i++) {
            for (int j = 0; j < nodesMatrix.length; j++) {
                nodesMatrix[i][j] = 0;
            }
        }
        int edges = scan.nextInt();
        for (int i = 0; i < edges; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            nodesMatrix[x][y] = 1;
        }
        int queries = scan.nextInt();
        for (int i = 0; i < queries; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            if(nodesMatrix[x][y] == 1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        scan.close();
    }
}
