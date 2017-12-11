package geeksforgeeks.basic;

import java.util.ArrayList;

public class LowestCommonAncestor {

    private ArrayList<Integer> path1 = new ArrayList<>();
    private ArrayList<Integer> path2 = new ArrayList<>();

    public static void main(String[] args) {
    }

    int lca(Node root, int n1, int n2) {
        if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
            System.out.println((path1.size() > 0) ? "n1 is present" : "n1 is missing");
            System.out.println((path2.size() > 0) ? "n2 is present" : "n2 is missing");
            return -1;
        }
        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (!path1.get(i).equals(path2.get(i)))
                break;
        }

        return path1.get(i - 1);
    }

    boolean findPath(Node node, int n, ArrayList<Integer> path) {
        if (node == null) {
            return false;
        }
        path.add(node.data);
        if (node.data == n) {
            return true;
        }
        if (node.left != null && findPath(node.left, n, path)) {
            return true;
        }
        if (node.right != null && findPath(node.right, n, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

}
