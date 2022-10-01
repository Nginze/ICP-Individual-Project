package main;

import java.util.Objects;
import java.util.Stack;

/**
 * 
 * A class that represents a node in a graph.
 * @author Jonathan
 */
public class Node {
    private Node parent;
    private Airport airport;
    private Airline airline;
    private Integer stops;
    private Double distance;

   
   // This is the constructor of the Node class. It is used to create a new Node object.
    public Node(Airport airport) {
        this.parent= null;
        this.airline = null;
        this.airport = airport;
        this.distance = 0D;
        this.stops = 0;
    }

    // This is a constructor of the Node class. It is used to create a new Node object.
    public Node(Node parent, Airport airport, Airline airline,Integer stops ) {
        this.parent = parent;
        this.airport = airport;
        this.airline = airline;
        this.stops = stops;
        this.distance = 0D;
    }

    /**
     * This function returns the parent of the current node
     * 
     * @return The parent node of the current node.
     */

    public Node getParent() {
        return parent;
    }
    
    /**
     * It returns the airport object.
     * 
     * @return The airport object.
     */

    public Airport getAirport() {
        return airport;
    }

   /**
    * This function returns the airline object
    * 
    * @return The airline object.
    */
    public Airline getAirline() {
        return airline;
    }

   /**
    * This function returns the distance between the current location and the location of the given
    * place
    * 
    * @return The distance between the two points.
    */
    public Double getDistance() {
        return distance;
    }

   /**
    * This function sets the distance of the current object to the distance passed in as a parameter
    * 
    * @param distance The distance between the two points in meters.
    */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * The function returns a stack of nodes that represents the path from the current node to the root
     * node
     * 
     * @return The path from the initial state to the goal state.
     */
    public Stack<Node> getSolutionPath(){
        Node node = this;
        Stack<Node> path = new Stack<>();
        while(node != null){
           path.push(node);
           node = node.parent;
        }
        return path;
    }

    /**
     * This function returns the number of stops in the route
     * 
     * @return The stops variable is being returned.
     */
    public Integer getStops() {
        return stops;
    }
    
    @Override
    public String toString() {
        return "Node{" +
                "parent=" + parent +
                ", airport=" + airport +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return ((Node) o).getAirport().getIataCode().equals(this.getAirport().getIataCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAirport());
    }

}
