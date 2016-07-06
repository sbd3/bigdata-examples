package java8.examples.linked.list;

public class ReverseLinkedList {

    public static void main(String[] args) {
        Node node1 = Node.getDummyList();
        
        printLinkedList(node1);
        printLinkedList(reverseList(node1));
    }

    private static Node reverseList(Node list) {
        if(list.next == null) {
            return list;
        }
        Node remainingNode = reverseList(list.next);
        list.next.next = list;
        list.next = null;
        return remainingNode;
    }
    
    private static void printLinkedList(Node list) {
        Node node = list;
        System.out.println("--------");
        while(node != null) {
            System.out.println(node.value);
            node = node.next;
        }
        System.out.println("--------");
    }
}
