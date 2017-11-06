package geeksforgeeks.basic;

import java.util.ArrayList;

public class DetectLoop {

    ArrayList<LLNode> nodes = new ArrayList<>();
    int detectLoop(LLNode head) {
        while(head != null) {
            if(nodes.contains(head)) {
                return 1;
            }
            nodes.add(head);
            head = head.next;
        }
        return 0;
    }
}

class LLNode {
    int data;
    LLNode next;

    LLNode(int d) {
        data = d;
        next = null;
    }
}