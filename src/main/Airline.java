package main;

public class Airline {
    private Integer airlineId;
    private String airlineName;
    private String airlineAlias;
    private String iataCode;
    private String icaoCode;
    private String callsign;
    private String country;
    private Boolean isActive;

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

    public Integer getAirlineId() {
        return airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getAirlineAlias() {
        return airlineAlias;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public String getCallsign() {
        return callsign;
    }

    public String getCountry() {
        return country;
    }

    public Boolean getActive() {
        return isActive;
    }
}
