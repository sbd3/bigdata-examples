package java8.pro.probs;

import java.util.List;

public class Tree {
    
    private Node root;

    public Tree(long rootData) {
        this.root = new Node(rootData);
    }

    public Node getRoot() {
        return root;
    }

}

class Node {
    private long data;
    private List<Node> children;
    
    public Node(Long data) {
        this.data = data;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "{ \"id\" : " + data + ", \"upstream_device_id\" : " + children + "}";
    }
    
    
    
}