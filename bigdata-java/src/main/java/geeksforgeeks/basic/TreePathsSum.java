package geeksforgeeks.basic;

public class TreePathsSum {

    static int rs = 0;

    public static int treePathsSum(Tree root) {
        rs = 0;
        traverse(root, 0);
        return rs;
    }

    public static void traverse(Tree root, int sum) {
        if (root == null) {
            return;
        }
        sum = sum * 10 + root.data;
        if(root.left == null && root.right == null) {
            rs += sum;
        } else {
            traverse(root.left, sum);
            traverse(root.right, sum);
        }
    }
    
    class Tree {
        int data;
        Tree left, right;

        Tree(int d) {
            data = d;
            left = null;
            right = null;
        }
    }
}