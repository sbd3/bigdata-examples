package misc;

public class Stack {

    StackNode top;
    StackNode pop() {
        if(top == null) {
            return null;
        }
        StackNode del = top;
        top = top.next;
        return del;
        
    }
    
    StackNode push(int data) {
        StackNode t = new StackNode(data);
        t.next = top;
        top = t;
        return top;
    }
}

class StackNode {
    StackNode next;
    int data;
    public StackNode(int data) {
        this.data = data;
    }
}