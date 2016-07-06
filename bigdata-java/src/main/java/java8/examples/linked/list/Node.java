package java8.examples.linked.list;

public class Node {
    
    public int value;
    public Node next;
    
    public Node(int value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "LinkedListNode [value=" + value + "]";
    }
    
    public static Node getDummyList() {
        Node node1 = new Node(5);
        Node node2 = new Node(4);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node6 = new Node(1);
        Node node7 = new Node(0);
        Node node8 = new Node(6);
        Node node9 = new Node(7);
        
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        return node1;
    }
}
