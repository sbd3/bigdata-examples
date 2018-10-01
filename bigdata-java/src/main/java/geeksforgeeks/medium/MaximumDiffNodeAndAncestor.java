package geeksforgeeks.medium;

import java.util.Scanner;

public class MaximumDiffNodeAndAncestor {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		while (t-- > 0) {
			int n = sc.nextInt();
			if (n == 0) {
				System.out.println(0);
				continue;
			}
			BTreeNode root = null;
			for (int i = 0; i < n; i++) {
				int a = sc.nextInt();
				int a1 = sc.nextInt();
				char lr = sc.next().charAt(0);
				if (i == 0) {
					root = new BTreeNode(a);
					switch (lr) {
					case 'L':
						root.left = new BTreeNode(a1);
						break;
					case 'R':
						root.right = new BTreeNode(a1);
						break;
					}
				} else {
					insert(root, a, a1, lr);
				}
			}

			MaximumDiffNodeAndAncestor g = new MaximumDiffNodeAndAncestor();
			System.out.println(g.maxDiff(root));
		}
		sc.close();
	}

	public static void insert(BTreeNode root, int a, int a1, char lr) {
		if (root == null) {
			return;
		}
		if (root.data == a) {
			switch (lr) {
			case 'L':
				root.left = new BTreeNode(a1);
				break;
			case 'R':
				root.right = new BTreeNode(a1);
				break;
			}
			return;
		}
		insert(root.left, a, a1, lr);
		insert(root.right, a, a1, lr);
	}

	int maxDiff(BTreeNode root) {
		return calculate(root, Integer.MIN_VALUE, Integer.MIN_VALUE + root.data);
	}

	int calculate(BTreeNode node, int diff, int max) {
		if(node == null) {
			return diff;
		}
		diff = Math.max(max - node.data, diff);
		max = Math.max(max, node.data);
		diff = calculate(node.left, diff, max);
        diff = calculate(node.right, diff, max);
        return diff;
	}

}

class BTreeNode {
	int data;
	BTreeNode left;
	BTreeNode right;

	public BTreeNode(int val) {
		this.data = val;
	}

}