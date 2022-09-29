package main;

import java.util.Stack;

public class Node {
    private Node parent;
    private Airport airport;

    private Airline airline;

    public Node(Airport airport) {
        this.parent= null;
        this.airline = null;
        this.airport = airport;
    }

    public Node(Node parent, Airport airport, Airline airline) {
        this.parent = parent;
        this.airport = airport;
        this.airline = airline;
    }

    public Node getParent() {
        return parent;
    }

    public Airport getAirport() {
        return airport;
    }

    public Airline getAirline() {
        return airline;
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
