package geeksforgeeks.basic;

public class SumTree {

    boolean isSumTree(Node1 node) {
        if(isLeafNode(node)) {
            return true;
        }
        return isSumTree(node.left) && isSumTree(node.right);
    }
    
    boolean isLeafNode(Node1 node) {
        return node != null && node.left == null && node.right == null;
    }
    
}
