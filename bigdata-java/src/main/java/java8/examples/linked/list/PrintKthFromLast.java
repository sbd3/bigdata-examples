package java8.examples.linked.list;

public class PrintKthFromLast {

	public static void main(String[] args) {
		
	}
	
	private int printKthNode(LinkedListNode head, int k) {
		if(head == null)
			return 0;
		int index = printKthNode(head.next, k) + 1;
		if(index == k) {
			System.out.println(head.data);
		}
		return index;
	}
	
	private class LinkedListNode {
		int data;
		LinkedListNode next;
	}

}
