package main;

import java.io.*;
import java.util.*;

public class FlightPath {
    private FileReader inputFile;
    private FileReader outputFile;

    public FlightPath( String inputFileName, String outputFileName) throws FileNotFoundException {
        this.inputFile = new FileReader(inputFileName);
        this.outputFile = new FileReader(outputFileName);
    }
    public HashMap<String, Airport> getSourceAndDestinationAirportMeta() throws IOException {
       BufferedReader inputData = new BufferedReader(this.inputFile);
       BufferedReader airportCSV = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\ICP project\\src\\resources\\airports.csv"));
       String record;
       HashMap<String, Airport> airports = new HashMap<>();
       String[] sourceData = inputData.readLine().split(",");
       String[] destinationData = inputData.readLine().split(",");
       String sourceCity = sourceData[0];
       String sourceCountry = sourceData[1];
       String destinationCity = destinationData[0];
       String destinationCountry = destinationData[1];

       while((record = airportCSV.readLine()) != null){
            String[] recordToList = record.split(",");
            Integer currentAirportId = Integer.parseInt(recordToList[0]);
            String currentAirportName = recordToList[1];
            String currentAirportCity = recordToList[2];
            String currentAirportCountry = recordToList[3];
            String iataCode = recordToList[4];
            String icaoCode = recordToList[5];
//            Double latitude = Double.parseDouble(recordToList[6]);
//            Double longitude = Double.parseDouble(recordToList[7]);
//            Integer altitude = Integer.parseInt(recordToList[8]);

            if(airports.size() > 1){
               return airports;
            }

            if(currentAirportCountry.equals(destinationCountry) && currentAirportCity.equals(destinationCity)){
              airports.put("destination", new Airport(currentAirportId, currentAirportName, currentAirportCity, currentAirportCountry, iataCode, icaoCode ) );
            }

            if((currentAirportCountry.equals(sourceCountry) && currentAirportCity.equals(sourceCity))){
                airports.put("source", new Airport(currentAirportId, currentAirportName, currentAirportCity, currentAirportCountry, iataCode, icaoCode ));
            }
       }
       System.out.println("No record found");
       return airports;
    }

    public Airport getAirportById(String airportCode) throws IOException {
        BufferedReader airportCSV = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\ICP project\\src\\resources\\airports.csv"));
        String record;
        while((record = airportCSV.readLine()) != null) {
            String[] recordToList = record.split(",");
            Integer currentAirportId = Integer.parseInt(recordToList[0]);
            String currentAirportName = recordToList[1];
            String currentAirportCity = recordToList[2];
            String currentAirportCountry = recordToList[3];
            String iataCode = recordToList[4];
            String icaoCode = recordToList[5];
//            Double latitude = Double.parseDouble(recordToList[6]);
//            Double longitude = Double.parseDouble(recordToList[7]);
//            Integer altitude = Integer.parseInt(recordToList[8]);

            if (iataCode.equals(airportCode)) {
                return new Airport(currentAirportId, currentAirportName, currentAirportCity, currentAirportCountry, iataCode, icaoCode);
            }
        }
        return null;
    }

    //implementation of BFS
    public Node getFlightPath(Airport source, Airport destination) throws IOException {
        Queue<Node> routesFrontier = new LinkedList<>();
        routesFrontier.add(new Node(source));
//        System.out.println(destination.getIataCode());
        while(!routesFrontier.isEmpty()){
            Node current = routesFrontier.poll();
            for(Route route: getAvailableRoutes(current.getAirport())){
                Airport flightDestination = getAirportById(route.getDestinationAirportCode());
                System.out.println((flightDestination));
                Node child = new Node(current, flightDestination);
                if(route.getDestinationAirportCode().equals(destination.getIataCode())){
                    return child;
                }
                routesFrontier.add(child);
            }
        }
        System.out.println("didnt find ");
        return null;
    }

    public List<Route> getAvailableRoutes(Airport airport) throws IOException {
        BufferedReader routesCSV = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\ICP project\\src\\resources\\routes.csv"));
        List<Route> availableRoutes = new ArrayList();
        String record;
        while((record = routesCSV.readLine()) != null){
            String[] recordToList = record.split(",");
            String currentAirlineCode = recordToList[0];
//            Long currentAirlineId = Long.parseLong(recordToList[1]);
            String currentSourceCode = recordToList[2];
//            Long currentSourceId = Long.parseLong(recordToList[3]);
            String currentDestinationCode = recordToList[4];
//            Long currentDestinationId = !recordToList[5].equals("") ? Long.parseLong(recordToList[5]) : -1;
//            Integer currentStops =recordToList[6] != "" ? Integer.parseInt(recordToList[6]) : 0;

            if(airport.getIataCode().equals(currentSourceCode)){
                availableRoutes.add(new Route(currentAirlineCode , currentSourceCode, currentDestinationCode));
            }
        }
        return availableRoutes;
    }

    public static void main(String[] args) throws IOException {

        FlightPath fp = new FlightPath("C:\\Users\\User\\Desktop\\ICP project\\src\\main\\input.txt", "C:\\Users\\User\\Desktop\\ICP project\\src\\main\\output.txt");
        HashMap<String, Airport> airports = fp.getSourceAndDestinationAirportMeta();
        Stack<Node> path = fp.getFlightPath(airports.get("source"), airports.get("destination")).getSolutionPath();
        while(!path.isEmpty()){
            System.out.println(path.pop().getAirport());
        }
    }
}