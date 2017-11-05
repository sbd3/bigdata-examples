package geeksforgeeks.basic;

public class LinkedListMiddleNode {

    static int getMiddle(Node2 head) {
        Node2 slow = head;
        Node2 fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }
}

class LinkedList {
    Node2 head;
}

class Node2 {
    int data;
    Node2 next;

    Node2(int d) {
        data = d;
        next = null;
    }
}
