package geeksforgeeks.basic;

import java.util.LinkedList;

public class FoldableTree {

    private static LinkedList<String> directions = new LinkedList<>();
    
    public static void main(String[] args) {
        FoldableTree tree = new FoldableTree();
        Node root = tree.new Node(10);
        root.left = tree.new Node(7);
        root.right = tree.new Node(15);
        root.left.left = tree.new Node(9);
        root.right.left = tree.new Node(11);
        
        tree.isFoldable(root);
    }
    
    private void isFoldable(Node root) {
        if(root == null || (root.left == null && root.right == null)) {
            System.out.println("Yes");
        }
        populateStack(root.left);
        popStack(root.right);
        if(directions.size() != 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
    
    private void populateStack(Node node) {
        if(node == null) {
            return;
        }
        if(node.left != null) {
            directions.add("R");
            populateStack(node.left);
        }
        if(node.right != null) {
            directions.add("L");
            populateStack(node.right);
        }
    }
    
    private void popStack(Node node) {
        if(node == null) {
            return;
        }
        if(node.left != null && directions.peek().equals("L")) {
            directions.pop();
            popStack(node.left);
        }
        if(node.right != null && directions.peek().equals("R")) {
            directions.pop();
            popStack(node.right);
        }
    }
    
    class Node {
        Node left;
        Node right;
        int val;
        
        public Node(int val) {
            this.val = val;
        }
    }
}
