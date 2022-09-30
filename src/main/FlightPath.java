package main;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * It takes in a source and destination airport and returns the optimal flight path between the two
 * airports
 */
public class FlightPath {

    String projectDir = Paths.get("").toAbsolutePath().toString();
    private FileReader inputFile;
    private FileWriter outputFile;

    public FlightPath( String inputFileName, String outputFileName) throws IOException {
        this.inputFile = new FileReader(inputFileName);
        this.outputFile = new FileWriter(outputFileName);
    }
    /**
     * It reads the input file and the airports.csv file and returns a HashMap with the source and
     * destination airports
     * 
     * @return A HashMap of type String, Airport.
     */
    public HashMap<String, Airport> getSourceAndDestinationAirportMeta() throws IOException, NumberFormatException {
       BufferedReader inputData = new BufferedReader(this.inputFile);
       BufferedReader airportCSV = new BufferedReader(new FileReader(projectDir + "/src/resources/airports.csv"));
       String record;
       HashMap<String, Airport> airports = new HashMap<>();
       String[] sourceData = inputData.readLine().split(",");
       String[] destinationData = inputData.readLine().split(",");
       String sourceCity = sourceData[0];
       String sourceCountry = sourceData[1];
       String destinationCity = destinationData[0];
       String destinationCountry = destinationData[1];

       while((record = airportCSV.readLine()) != null){
            String[] recordToList = record.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            Integer currentAirportId = Integer.parseInt(recordToList[0]);
            String currentAirportName = recordToList[1];
            String currentAirportCity = recordToList[2];
            String currentAirportCountry = recordToList[3];
            String iataCode = recordToList[4];
            String icaoCode = recordToList[5];
            Double latitude = Double.parseDouble(recordToList[6]);
            Double longitude = Double.parseDouble(recordToList[7]);

            if(airports.size() > 1){
               return airports;
            }

            if(currentAirportCountry.equals(destinationCountry) && currentAirportCity.equals(destinationCity)){
              airports.put("destination", new Airport(currentAirportId, currentAirportName, currentAirportCity, currentAirportCountry, iataCode, icaoCode, latitude, longitude ) );
            }

            if((currentAirportCountry.equals(sourceCountry) && currentAirportCity.equals(sourceCity))){
                airports.put("source", new Airport(currentAirportId, currentAirportName, currentAirportCity, currentAirportCountry, iataCode, icaoCode, latitude, longitude ));
            }
       }
       System.out.println("Airports with input credentials cannot be found");
       return airports;
    }

    /**
     * It reads the CSV file and returns an Airport object if the airportCode or airportId matches the
     * one in the CSV file
     * 
     * @param airportCode The IATA code of the airport
     * @param airportId The ID of the airport
     * @return The method is returning an Airport object.
     */
    public Airport getAirportById(String airportCode, String airportId) throws IOException, NumberFormatException{
        BufferedReader airportCSV = new BufferedReader(new FileReader(projectDir + "/src/resources/airports.csv"));
        String record;
        while((record = airportCSV.readLine()) != null) {
            String[] recordToList = record.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            String currentAirportId = recordToList[0];
            String currentAirportName = recordToList[1];
            String currentAirportCity = recordToList[2];
            String currentAirportCountry = recordToList[3];
            String iataCode = recordToList[4];
            String icaoCode = recordToList[5];
            Double latitude = Double.parseDouble(recordToList[6]);
            Double longitude = Double.parseDouble(recordToList[7]);

            if (iataCode.equals(airportCode) || currentAirportId.equals(airportId)) {
                return new Airport(Integer.parseInt(currentAirportId), currentAirportName, currentAirportCity, currentAirportCountry, iataCode, icaoCode, latitude, longitude);
            }
        }
        return null;
    }

    /**
     * This function takes in an airline code and an airline id and returns an airline object
     * 
     * @param airlineCode The IATA code of the airline.
     * @param airlineId The unique identifier of the airline.
     * @return The method is returning an object of type Airline.
     */
    public Airline getAirlineById(String airlineCode, String airlineId) throws IOException {
        BufferedReader airlineCSV = new BufferedReader(new FileReader(projectDir + "/src/resources/airlines.csv"));
        String record;
        while((record = airlineCSV.readLine()) != null) {
            String[] recordToList = record.split(",");
            String currentAirlineId = recordToList[0];
            String iataCode = recordToList[3];

            if (iataCode.equals(airlineCode) || currentAirlineId.equals(airlineId)) {
                return new Airline(Integer.parseInt(currentAirlineId), iataCode);
            }
        }
        return null;
    }

    /**
     * This function takes in a source and destination airport and returns a node containing the flight
     * path from the source to the destination
     * 
     * @param source The source airport
     * @param destination The destination airport
     * @return A node object
     */
    public Node getFlightPath(Airport source, Airport destination) throws IOException {
        Queue<Node> routesFrontier = new LinkedList<>();
        routesFrontier.add(new Node(source));
        HashSet<String> explored = new HashSet<>();
        HashSet<String> blacklist = new HashSet<>();
        while(!routesFrontier.isEmpty()){
            Node current = routesFrontier.poll();
            explored.add(current.getAirport().getIataCode());
            for(Route route: getAvailableRoutes(current.getAirport())){
                    if(!blacklist.contains(route.getDestinationAirportCode())){
                        try{
                            Airport flightDestination = getAirportById(route.getDestinationAirportCode(), route.getDestinationAirportId());
                            Airline flight = getAirlineById(route.getAirlineCode(), route.getAirlineId());
                            Node child = new Node(current, flightDestination, flight, route.getStops());
                            if(route.getDestinationAirportCode().equals(destination.getIataCode())){
                                return child;
                            }
                            if(!explored.contains(child.getAirport().getIataCode()) && !routesFrontier.contains(child)){
                                routesFrontier.add(child);
                            }
                        }catch(NullPointerException npe){
                           blacklist.add(route.getDestinationAirportCode());
                        }

                    }
            }
        }
        System.out.println("No routes to destination found!");
        return null;
    }

   /**
    * It takes in a source and destination airport, and returns the optimal flight path between the two
    * airports
    * 
    * @param source The source airport
    * @param destination The destination airport
    * @return The optimal flight path from the source airport to the destination airport.
    */
    public Node getOptimalFlightPath(Airport source, Airport destination) throws IOException {
        PriorityQueue<Node> routesFrontier = new PriorityQueue<>((node1, node2) -> {
            if (node1.getDistance() < node2.getDistance()) {
                return -1;
            }
            if (node1.getDistance() > node2.getDistance()) {
                return 1;
            }
            return 0;
        });
        HashSet<String> blacklist = new HashSet<>();
        HashSet<String> explored = new HashSet<>();
        Node startAirport = new Node(source);
        routesFrontier.add(startAirport);
        while(!routesFrontier.isEmpty()){
            Node current = routesFrontier.poll();
            System.out.println(current.getDistance());
            explored.add(current.getAirport().getIataCode());
            if(current.getAirport().getIataCode().equals(destination.getIataCode())){
                return current;
            }
            for(Route route: getAvailableRoutes(current.getAirport())){
                if(!blacklist.contains(route.getDestinationAirportCode())){
                    try{
                        Airport flightDestination = getAirportById(route.getDestinationAirportCode(), route.getDestinationAirportId());
                        Double distance = distance(flightDestination.getLatitude(), flightDestination.getLongitude(), destination.getLatitude(), destination.getLongitude(), 'K') + distance(current.getAirport().getLatitude(), current.getAirport().getLongitude(), flightDestination.getLatitude(), flightDestination.getLongitude(), 'K') + current.getDistance();
                        Airline flight = getAirlineById(route.getAirlineCode(), route.getAirlineId());
                        Node child = new Node(current, flightDestination, flight, route.getStops());
                        child.setDistance(distance);
                        if(!explored.contains(child.getAirport().getIataCode())){
                            routesFrontier.add(child);
                        }
                    }catch(NullPointerException npe){
                        blacklist.add(route.getDestinationAirportCode());
                        System.out.println(route.getDestinationAirportCode());
                    }
                }
            }
        }
        return null;
    }


    /**
     * It reads a CSV file and returns a list of routes that are available from a given airport
     * 
     * @param airport the airport object that is passed to the method
     * @return A list of routes
     */
    public List<Route> getAvailableRoutes(Airport airport) throws IOException {
        BufferedReader routesCSV = new BufferedReader(new FileReader(projectDir + "/src/resources/routes.csv"));
        List<Route> availableRoutes = new ArrayList();
        String record;
        while((record = routesCSV.readLine()) != null){
            String[] recordToList = record.split(",");
            String currentAirlineCode = recordToList[0];
            String currentAirlineId = recordToList[1];
            String currentSourceCode = recordToList[2];
            String currentDestinationCode = recordToList[4];
            String currentDestinationId = recordToList[5];
            Boolean codeshare = recordToList[6].equals("Y") && !recordToList[4].equals("DOH") ? true : false;

            Integer currentStops = Integer.parseInt(recordToList[7]) ;

            if(airport.getIataCode().equals(currentSourceCode)){
                availableRoutes.add(new Route(currentAirlineId ,currentAirlineCode , currentSourceCode, currentDestinationCode, currentDestinationId, codeshare, currentStops));
            }
        }
        return availableRoutes;
    }

    /**
     * The Haversine formula determines the great-circle distance between two points on a sphere given
     * their longitudes and latitudes
     * 
     * @param sourceLat Latitude of the source location
     * @param sourceLng The longitude of the source location.
     * @param destinationLat Latitude of the destination
     * @param destinationLng The longitude of the destination point.
     * @return The distance in kilometers between two points on the Earth.
     */
    public Long getDistanceInKilometers(Double sourceLat, Double sourceLng, Double destinationLat, Double destinationLng){
        final Integer AVERAGE_EARTH_RADIUS = 6371;

        Double latDistance = (sourceLat - destinationLat);
        Double lngDistance = (sourceLng - destinationLng);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(sourceLat)
                * Math.cos(destinationLat) * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Math.round(AVERAGE_EARTH_RADIUS * c);

    }

   /**
    * The distance between two points on the surface of a sphere is the arc length of the great circle
    * path between them
    * 
    * @param lat1 Latitude of point 1 (in decimal degrees)
    * @param lon1 longitude of the first point
    * @param lat2 latitude of the second point
    * @param lon2 the longitude of the destination
    * @param unit The unit you desire for results.
    * @return The distance between two points on the Earth.
    */
    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    /**
     * It takes a stack of nodes and writes the data to a file
     * 
     * @param path Stack of nodes
     */
    public void outputDataToFile(Stack<Node> path) throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(outputFile);
        Double TotalDistance = 0D;
        Integer TotalFlights = path.size() - 1;
        while(!path.isEmpty()){
            Node node = path.pop();
            try{
                TotalDistance = Math.max(TotalDistance, node.getDistance());
                if(node.getParent() != null){
                    if(node.getAirline() != null){
                        outputWriter.write(node.getAirline().getIataCode() + " from " + node.getParent().getAirport().getIataCode() + " to " + node.getAirport().getIataCode() + " " + node.getStops() + " stops " +  "\n");
                    }else{
                        outputWriter.write("N/A"+ " from " + node.getParent().getAirport().getIataCode() + " to " + node.getAirport().getIataCode() + " " + node.getStops() + " stops " + "\n");
                    }
                }
            }catch(NullPointerException npe){
                System.out.println("this is node is causing");
                System.out.println(node.getAirline());
               npe.printStackTrace();
            }
        }
        outputWriter.write("Total Flights: " + TotalFlights + "\n");
        outputWriter.write("Total Distance: " + TotalDistance + "Km" + "\n");
        outputWriter.write("Optimality criteria: Distance");
        outputWriter.close();
    }
    public static void main(String[] args) throws IOException {
        String userPath = Paths.get("").toAbsolutePath().toString();
        try{
            FlightPath fp = new FlightPath(userPath + "/src/main/input.txt", userPath + "/src/main/output.txt");
            HashMap<String, Airport> airports = fp.getSourceAndDestinationAirportMeta();
            Stack<Node> path = fp.getOptimalFlightPath(airports.get("source"), airports.get("destination")).getSolutionPath();
            fp.outputDataToFile(path);
        }catch(NullPointerException npe){
           npe.printStackTrace();
        }catch(IOException ie){
            ie.printStackTrace();
        }


    }
}