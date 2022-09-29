package main;

public class Route {
    private String airlineCode;
//    private Long airlineId;
    private String sourceAirportCode;
//    private Long sourceAirportId;
    private String destinationAirportCode;
    private String destinationAirportId;
//    private Integer stops;

    public Route(String airlineCode, String sourceAirportCode, String destinationAirportCode, String destinationAirportId) {
        this.airlineCode = airlineCode;
//        this.airlineId = airlineId;
        this.sourceAirportCode = sourceAirportCode;
//        this.sourceAirportId = sourceAirportId;
        this.destinationAirportCode = destinationAirportCode;
//        this.destinationAirportId = destinationAirportId;
//        this.stops = stops;
        this.destinationAirportId = destinationAirportId;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

//    public Long getAirlineId() {
//        return airlineId;
//    }

    public String getSourceAirportCode() {
        return sourceAirportCode;
    }

//    public Long getSourceAirportId() {
//        return sourceAirportId;
//    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public String getDestinationAirportId() {
        return destinationAirportId;
    }

//    public Long getDestinationAirportId() {
//        return destinationAirportId;
//    }
//
//    public Integer getStops() {
//        return stops;
//    }
}
