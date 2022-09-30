package main;

/**
 * This class is used to store information about an airline
 */
public class Airline {
    private Integer airlineId;
    private String airlineName;
    private String airlineAlias;
    private String iataCode;
    private String icaoCode;
    private String callsign;
    private String country;
    private Boolean isActive;

    // This is a constructor. It is a special method that is called when an object is created.
    public Airline(Integer airlineId, String iataCode) {
        this.airlineId = airlineId;
        this.iataCode = iataCode;
    }

    // This is a constructor. It is a special method that is called when an object is created.
    public Airline(Integer airlineId, String airlineName, String airlineAlias, String iataCode, String icaoCode, String callsign, String country, Boolean isActive) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.airlineAlias = airlineAlias;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.callsign = callsign;
        this.country = country;
        this.isActive = isActive;
    }

    /**
     * This function returns the airlineId of the flight.
     * 
     * @return The airlineId
     */
    public Integer getAirlineId() {
        return airlineId;
    }

    /**
     * This function returns the name of the airline
     * 
     * @return The airline name.
     */
    public String getAirlineName() {
        return airlineName;
    }

   /**
    * This function returns the airline alias
    * 
    * @return The airlineAlias is being returned.
    */
    public String getAirlineAlias() {
        return airlineAlias;
    }

   /**
    * This function returns the IATA code of the airport
    * 
    * @return The iataCode is being returned.
    */
    public String getIataCode() {
        return iataCode;
    }

   /**
    * This function returns the ICAO code of the airport
    * 
    * @return The icaoCode is being returned.
    */
    public String getIcaoCode() {
        return icaoCode;
    }

   /**
    * This function returns the callsign of the aircraft
    * 
    * @return The callsign of the aircraft.
    */
    public String getCallsign() {
        return callsign;
    }

 /**
  * This function returns the country of the user
  * 
  * @return The country variable is being returned.
  */
    public String getCountry() {
        return country;
    }

   /**
    * This function returns a boolean value that indicates whether the user is active or not
    * 
    * @return The value of the isActive variable.
    */
    public Boolean getActive() {
        return isActive;
    }
}
