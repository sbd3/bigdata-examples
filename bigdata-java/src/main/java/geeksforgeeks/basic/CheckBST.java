package geeksforgeeks.basic;

public class CheckBST {

    Node1 root;
    
    public static void main(String[] args) {
        CheckBST tree = new CheckBST();
        tree.root = new Node1(4);
        tree.root.left = new Node1(2);
        tree.root.right = new Node1(5);
        tree.root.left.left = new Node1(1);
        tree.root.left.right = new Node1(30);
        tree.root.left.right.left = new Node1(1);
        tree.root.left.right.right = new Node1(10);
 
        System.out.println(findMax(tree.root.left));
        /*if (tree.isBST(tree.root))
            System.out.println("IS BST");
        else
            System.out.println("Not a BST");*/
    }

    boolean isBST(Node1 node) {
        if(node == null) {
            return true;
        }
        if((node.left != null && node.left.data > node.data) || (node.right != null && node.right.data < node.data)) {
            return false;
        }
        return isBST(node.left) && isBST(node.right);
    }
    
    static int findMax(Node1 node) {
        if(node == null) {
            return 0;
        }
        int nodeMax = 0, max = 0;
        if(node.left != null)
        nodeMax = findMax(node.left);
        if(node.right != null)
        nodeMax = findMax(node.right);
        if(node.left != null)
        max = Math.max(node.data, node.left.data);
        if(node.right != null)
        max = Math.max(max, node.right.data);
        return Math.max(nodeMax, max);
    }
}
