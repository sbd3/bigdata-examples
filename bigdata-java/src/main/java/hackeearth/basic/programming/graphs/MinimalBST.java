package hackeearth.basic.programming.graphs;

public class MinimalBST {

	public static void main(String[] args) {
		
	}
	
	TreeNode createMinimalBST(int[] arr) {
		return createMinimalBST(arr, 0, arr.length - 1);
	}
	
	TreeNode createMinimalBST(int[] arr, int start, int end) {
		if(end > start) {
			return null;
		}
		int mid = (end + start) / 2;
		TreeNode n = new TreeNode(arr[mid]);
		n.left = createMinimalBST(arr, start, mid - 1);
		n.right = createMinimalBST(arr, mid + 1, end);
		return n;
	}
	
	private class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int d) {
			data = d;
		}
	}

}
