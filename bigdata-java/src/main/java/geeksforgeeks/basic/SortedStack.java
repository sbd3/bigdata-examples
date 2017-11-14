package geeksforgeeks.basic;

public class SortedStack {
    
    public static void main(String[] args) {
        SortedStack s = new SortedStack(30);
        s.push(-5);
        s.push(18);
        s.push(14);
        s.push(-3);
        s.peek();
        System.out.println("The stack is");
        s.display();
        System.out.println("The stack after sort is");
        s.sort();
        s.display();
    }
    
    public Node top;

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            next = null;
        }

    }

    public SortedStack(int data){
        top=new Node(data);
    }

    public boolean isEmpty() {
        return top == null ? true : false;
    }

    public void push(int x) {
        Node n = new Node(x);
        n.next = top;
        top = n;
    }

    public int pop() {
        if (top == null) {
            System.out.println("stack empty");
            return 0;
        }
        int t = top.data;
        top = top.next;
        return t;
    }

    public int peek() {
        return top.data;
    }

    public void display() {
        Node n = top;
        while (n != null) {
            System.out.println(n.data);
            n = n.next;
        }
    }

    public void sInsert(int d) {
        if (isEmpty()) {
            push(d);
            return;
        }
        int t = peek();
        if (d > t)
            push(d);
        else {
            t = pop();
            sInsert(d);
            push(t);
        }
    }

    public void sort() {
        if (isEmpty())
            return;

        else {
            int t = pop();
            sort();
            sInsert(t);
        }
    }
}

