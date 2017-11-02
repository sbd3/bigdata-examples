package geeksforgeeks.basic;

public class DiffOddEvenNodes {
    
    int evenSum;
    int oddSum;
    Node1 root;

    public static void main(String[] args) {
        DiffOddEvenNodes tree = new DiffOddEvenNodes();
        tree.root = new Node1(5);
        tree.root.left = new Node1(2);
        tree.root.right = new Node1(6);
        tree.root.left.left = new Node1(1);
        tree.root.left.right = new Node1(4);
        tree.root.left.right.left = new Node1(3);
        tree.root.right.right = new Node1(8);
        tree.root.right.right.right = new Node1(9);
        tree.root.right.right.left = new Node1(7);
        tree.computeSum(tree.root, 1);
        System.out.println((tree.oddSum - tree.evenSum) + " is the required difference");
    }
    
    void computeSum(Node1 node, int level) {
        if(node == null) {
            return;
        }
        if(level % 2 == 0) {
            evenSum += node.data;
        } else {
            oddSum += node.data;
        }
        computeSum(node.left, level + 1);
        computeSum(node.right, level + 1);
    }
}
