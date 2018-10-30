package geeksforgeeks.amazon;

import java.util.HashMap;
import java.util.Scanner;

public class VerticalOrderTree {
	
	class Node {
		int data;
		Node left, right;

		Node(int key) {
			data = key;
			left = right = null;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		VerticalOrderTree obj = new VerticalOrderTree();
		while (t-- > 0) {
			HashMap<Integer, Node> m = new HashMap<Integer, Node>();
			int n = sc.nextInt();
			Node root = null;
			while (n-- > 0) {
				int n1 = sc.nextInt();
				int n2 = sc.nextInt();
				char lr = sc.next().charAt(0);

				Node parent = m.get(n1);
				if (parent == null) {
					parent = obj.new Node(n1);
					m.put(n1, parent);
					if (root == null)
						root = parent;
				}
				Node child = obj.new Node(n2);
				if (lr == 'L')
					parent.left = child;
				else
					parent.right = child;
				m.put(n2, child);
			}

			GFG gfg = obj.new GFG();
			gfg.verticalOrder(root);
			System.out.println();
		}
		sc.close();
	}



	class GFG {
		void verticalOrder(Node root) {
			if(root == null) {
				return;
			}
			verticalOrder(root.left);
			System.out.print(root.data + " ");
			verticalOrder(root.right);
		}
	}

}
