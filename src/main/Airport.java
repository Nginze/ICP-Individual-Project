package main;

public class Airport {
    private Integer airportId;
    private String name;
    private String city;
    private String country;
    private String iataCode;
    private String icaoCode;
//    private Double latitude;
//    private Double longitude;
//    private Integer altitude;

    public Airport(Integer airportId, String name, String city, String country, String iataCode, String icaoCode) {
        this.airportId = airportId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.altitude = altitude;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public Integer getAltitude() {
//        return altitude;
//    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", icaoCode='" + icaoCode + '\'' +
//                ", latitude=" + latitude +
//                ", longitude=" + longitude +
//                ", altitude=" + altitude +
                '}';
    }

}
