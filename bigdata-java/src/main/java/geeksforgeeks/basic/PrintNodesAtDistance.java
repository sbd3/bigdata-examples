package geeksforgeeks.basic;

public class PrintNodesAtDistance {

    Node1 root;
    public static void main(String[] args) {
        PrintNodesAtDistance tree = new PrintNodesAtDistance();
        
        /* Constructed binary tree is
                1
              /   \
             2     3
            /  \   /
           4    5 8 
        */
        tree.root = new Node1(1);
        tree.root.left = new Node1(2);
        tree.root.right = new Node1(3);
        tree.root.left.left = new Node1(4);
        tree.root.left.right = new Node1(5);
        tree.root.right.left = new Node1(8);
  
        tree.printNodes(tree.root, 0);
    }
    
    void printNodes(Node1 node, int k) {
        if(node == null) {
            return;
        }
        if(k == 0) {
            System.out.print(node.data + " ");
            return;
        }
        printNodes(node.left, k - 1);
        printNodes(node.right, k - 1);
    }
}

class Node1 {
    int data;
    Node1 left, right;
    Node1(int item) {
        data = item;
        left = right = null;
    }
}