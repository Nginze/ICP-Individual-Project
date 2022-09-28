package main;

import java.util.Stack;

public class Node {
    private Node parent;
    private Airport airport;

    public Node(Airport airport) {
        this.parent= null;
        this.airport = airport;
    }

    public Node(Node parent, Airport airport) {
        this.parent = parent;
        this.airport = airport;
    }

    public Node getParent() {
        return parent;
    }

    public Airport getAirport() {
        return airport;
    }

    public Stack<Node> getSolutionPath(){
        Node node = this;
        Stack<Node> path = new Stack<>();
        while(node != null){
           path.push(node);
           node = node.parent;
        }
        return path;
    }
    @Override
    public String toString() {
        return "Node{" +
                "parent=" + parent +
                ", airport=" + airport +
                '}';
    }

}
