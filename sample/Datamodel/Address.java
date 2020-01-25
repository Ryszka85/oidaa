package sample.Datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Address {
    private SimpleStringProperty postal;
    private SimpleStringProperty streetAddress;
    private SimpleStringProperty city;
    private SimpleStringProperty countryName;

    public Address(String postal, String streetAddress, String city, String countryName) {
        this.postal = new SimpleStringProperty(postal);
        this.streetAddress = new SimpleStringProperty(streetAddress);
        this.city = new SimpleStringProperty(city);
        this.countryName = new SimpleStringProperty(countryName);
    }

    public String getPostal() {
        return postal.getValue();
    }

    public void setPostal(String postal) {
        this.postal.set(postal);
    }

    public String getStreetAddress() {
        return streetAddress.getValue();
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress.set(streetAddress);
    }

    public String getCity() {
        return city.getValue();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getCountryName() {
        return countryName.getValue();
    }

    public void setCountryName(String countryName) {
        this.countryName.set(countryName);
    }

    @Override
    public String toString() {
        return "Address{" +
                getPostal()  +
                ", " + getStreetAddress() +
                ", " + getCity() +
                ", " + getCountryName()  +
                '}';
    }
}
