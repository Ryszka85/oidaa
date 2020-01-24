package sample.Datamodel;

public class Address {
    private String postal;
    private String streetAddress;
    private String city;
    private String countryName;

    public Address(String postal, String streetAddress, String city, String countryName) {
        this.postal = postal;
        this.streetAddress = streetAddress;
        this.city = city;
        this.countryName = countryName;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Address{" +
                postal  +
                ", " + streetAddress +
                ", " + city +
                ", " + countryName  +
                '}';
    }
}
