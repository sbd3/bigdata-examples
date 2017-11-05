package geeksforgeeks.basic;

public class RootToLeafPathSum {

    boolean hasPathSum(Node1 node, int sum) {
        if(node != null && node.left == null && node.right == null && sum - node.data == 0) {
            return true;
        }
        if(node == null) {
            return false;
        }
        return hasPathSum(node.left, sum - node.data) || hasPathSum(node.right, sum - node.data);
    }
}
