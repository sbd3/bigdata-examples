package cracking.the.coding.interview.linkedlists;

/**
 * Implement an algorithm to find the kth to last element of a singly linked list.
 */
public class ReturnKthToLast {

	public static void main(String[] args) {
		ReturnKthToLast obj = new ReturnKthToLast();
		Node n1 = obj.new Node(1);
		Node n2 = obj.new Node(2);
		Node n3 = obj.new Node(3);
		Node n4 = obj.new Node(4);
		Node n5 = obj.new Node(5);
		Node n6 = obj.new Node(6);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		
		obj.printKthToLastRecursive(n1, 2);
		obj.printKthToLastLength(n1, 4);
	}
	
	private int printKthToLastLength(Node node, int k) {
		int len = 0;
		Node head = node;
		while(node != null) {
			node = node.next;
			len++;
		}
		while(len-- != 0) {
			head = head.next;
			if(k == len) {
				System.out.println(head.data);
			}
		}
		return 0;
	}
	
	private int printKthToLastRecursive(Node node, int k) {
		if(node == null)
			return 0;
		int index = printKthToLastRecursive(node.next, k) + 1;
		System.out.println("Index: " + index + " K:" + k);
		if(index == k) {
			System.out.println(node.data);
		}
		return index;
	}
	
	private class Node {
		Node next;
		int data;
		
		public Node(int data) {
			this.data = data;
		}
		
	}

}
