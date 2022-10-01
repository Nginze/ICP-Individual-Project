package main;

/**
 * 
 * The Airport class is a blueprint for creating objects that represent airports
 * @author Jonathan
 */
public class Airport {
    private Integer airportId;
    private String name;
    private String city;
    private String country;
    private String iataCode;
    private String icaoCode;
    private Double latitude;
    private Double longitude;

    // This is a constructor. It is a special method that is called when an object is created.
    public Airport(Integer airportId, String name, String city, String country, String iataCode, String icaoCode) {
        this.airportId = airportId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
    }

  // This is a constructor. It is a special method that is called when an object is created.
    public Airport(Integer airportId, String name, String city, String country, String iataCode, String icaoCode, Double latitude, Double longitude) {
        this.airportId = airportId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * This function returns the airportId of the airport
     * 
     * @return The airportId
     */
    public Integer getAirportId() {
        return airportId;
    }

    /**
     * This function returns the name of the person
     * 
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * This function returns the city of the user
     * 
     * @return The city.
     */
    public String getCity() {
        return city;
    }
    /**
     * This function returns the country of the user
     * 
     * @return The country of the person.
     */

    public String getCountry() {
        return country;
    }

    /**
     * It returns the IATA code of the airport.
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
     * This function returns the latitude of the location.
     * 
     * @return The latitude of the location.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * It returns the longitude of the location.
     * 
     * @return The longitude of the location.
     */
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", icaoCode='" + icaoCode + '\'' +
                ", latitude=" + latitude  +'\''+
                ", longitude=" + longitude +
                '}';
    }

}
