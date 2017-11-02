package geeksforgeeks.basic;

public class MaxDepthOfTree {
    Node root;
    
    public static void main(String[] args) {
        MaxDepthOfTree tree = new MaxDepthOfTree();
        
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
  
        System.out.println("Height of tree is : " + 
                                      tree.maxDepth(tree.root));
    }
    
    int maxDepth(Node node) {
        if(node == null) {
            return 0;
        }
        else {
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

}

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}
