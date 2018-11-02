package cracking.the.coding.interview.linkedlists;

import java.util.HashSet;

/**
 *  Write code to remove duplicates from an unsorted linked list.
 *  How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDups {

	public static void main(String[] args) {
		RemoveDups obj = new RemoveDups();
		Node n1 = obj.new Node(1);
		Node n2 = obj.new Node(1);
		Node n3 = obj.new Node(2);
		Node n4 = obj.new Node(3);
		Node n5 = obj.new Node(4);
		Node n6 = obj.new Node(1);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		
		obj.printList(n1);
		obj.removeDupsUsingHashing(n1);
		obj.removeDupsUsingRunner(n1);
		obj.printList(n1);
	}
	
	private void removeDupsUsingRunner(Node node) {
		Node current = node;
		while(current != null) {
			Node runner = current;
			while(runner.next != null) {
				if(current.data == runner.next.data) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}
	
	private void removeDupsUsingHashing(Node node) {
		HashSet<Integer> set = new HashSet<>();
		Node prev = null;
		while(node != null) {
			if(set.contains(node.data))
				prev.next = node.next;
			else {
				set.add(node.data);
				prev = node;
			}
			node = node.next;
		}
	}
	
	private void printList(Node node) {
		while(node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	private class Node {
		Node next;
		int data;
		
		public Node(int data) {
			this.data = data;
		}
		
	}
}

