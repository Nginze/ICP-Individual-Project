package main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String userPath = Paths.get("").toAbsolutePath().toString();
        try{
            FlightPath fp = new FlightPath(userPath + "/src/main/dubai-riyadh.txt");
            HashMap<String, Airport> airports = fp.getSourceAndDestinationAirportMeta();
            Stack<Node> path = fp.getOptimalFlightPath(airports.get("source"), airports.get("destination")).getSolutionPath();
            fp.outputDataToFile(path);
        }catch(IOException ie){
            ie.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
}
