package geeksforgeeks.basic;

public class IsomorphicTrees {
    
    Node1 root1;
    Node1 root2;

    public static void main(String[] args) {
        IsomorphicTrees tree = new IsomorphicTrees();
        // Let us create trees shown in above diagram
        tree.root1 = new Node1(1);
        tree.root1.left = new Node1(2);
        tree.root1.right = new Node1(3);
        tree.root1.left.left = new Node1(4);
        tree.root1.left.right = new Node1(5);
        tree.root1.right.left = new Node1(6);
        tree.root1.left.right.left = new Node1(7);
        tree.root1.left.right.right = new Node1(8);
  
        tree.root2 = new Node1(1);
        tree.root2.left = new Node1(3);
        tree.root2.right = new Node1(2);
        tree.root2.right.left = new Node1(4);
        tree.root2.right.right = new Node1(5);
        tree.root2.left.right = new Node1(6);
        tree.root2.right.right.left = new Node1(8);
        tree.root2.right.right.right = new Node1(7);
  
        if (tree.isIsomorphic(tree.root1, tree.root2))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
    
    boolean isIsomorphic(Node1 node1, Node1 node2) {
        if(node1 == null && node2 == null) {
            return true;
        } 
        if(node1 == null || node2 == null) {
            return false;
        }
        if (node1.data != node2.data)
            return false;
        return  (isIsomorphic(node1.left, node2.left) && isIsomorphic(node1.right, node2.right)) 
                        || (isIsomorphic(node1.left, node2.right) && isIsomorphic(node1.right, node2.left));
    }
}
