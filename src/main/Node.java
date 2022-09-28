package main;

public class Node {
    private Route parentAirport;
    private Route airport;

    public Node(Route airport) {
        this.parentAirport = null;
        this.airport = airport;
    }

    public Node(Route parentAirport, Route airport) {
        this.parentAirport = parentAirport;
        this.airport = airport;
    }

    public Route getParentAirport() {
        return parentAirport;
    }

    public Route getAirport() {
        return airport;
    }
}
