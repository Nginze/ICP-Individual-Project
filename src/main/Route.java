package main;

/**
 * It's a class that represents a route.
 */
public class Route {
    private String airlineCode;
    private String airlineId;
    private String sourceAirportCode;
    private String destinationAirportCode;
    private String destinationAirportId;
    private Boolean codeshare;
    private Integer stops;

    public Route(String airlineId, String airlineCode, String sourceAirportCode, String destinationAirportCode, String destinationAirportId, Boolean codeshare, Integer stops) {
        this.airlineCode = airlineCode;
        this.airlineId = airlineId;
        this.sourceAirportCode = sourceAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.codeshare = codeshare;
        this.stops = stops;
        this.destinationAirportId = destinationAirportId;
    }

   /**
    * This function returns the airline code
    * 
    * @return The airline code.
    */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * This function returns the airlineId of the flight.
    * 
    * @return The airlineId is being returned.
    */
    public String getAirlineId() {
        return airlineId;
    }

    /**
     * It returns the source airport code.
     * 
     * @return The sourceAirportCode is being returned.
     */
    public String getSourceAirportCode() {
        return sourceAirportCode;
    }

    /**
     * This function returns the destination airport code
     * 
     * @return The destination airport code.
     */
    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    /**
     * This function returns the destination airport id.
     * 
     * @return The destinationAirportId is being returned.
     */
    public String getDestinationAirportId() {
        return destinationAirportId;
    }

    /**
     * This function returns the codeshare value of the flight
     * 
     * @return The codeshare variable is being returned.
     */
    public Boolean getCodeshare() {
        return codeshare;
    }

    /**
     * This function returns the number of stops
     * 
     * @return The stops variable is being returned.
     */
    public Integer getStops() {
        return stops;
    }
}
