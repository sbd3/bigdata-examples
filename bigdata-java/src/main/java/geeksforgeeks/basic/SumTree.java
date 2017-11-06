package geeksforgeeks.basic;

public class SumTree {
    
    int sum(Node1 node) {
        if(node == null) {
            return 0;
        }
        return sum(node.left) + node.data + sum(node.right);
    }
    
    boolean isSumTree(Node1 node) {
        if(node == null || (node.left == null && node.right == null)) {
            return true;
        }
        int ls = sum(node.left);
        int rs = sum(node.right);
        
        return (node.data == ls + rs) && isSumTree(node.left) && isSumTree(node.right);
    }
    
}
