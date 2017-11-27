package misc;

public class CreatingALinkedList {

}

class ListNode {
    ListNode next = null;
    int data;
    public ListNode(int data) {
        this.data = data;
    }
    void appendToTail(int d) {
        ListNode tail = new ListNode(d);
        ListNode begin = this;
        while(begin.next != null) {
            begin = begin.next;
        }
        begin.next = tail;
    }
    ListNode deleteNode(ListNode head, int d) {
        ListNode n = head;
        if(n.data == d) {
            n = n.next;
            return n;
        }
        while(n.next != null) {
            if(n.next.data == d) {
                n.next = n.next.next;
                return head;
            }
            n= n.next;
        }
        return head;
    }
}
