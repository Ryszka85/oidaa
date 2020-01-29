package sample.Datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Country {
    private SimpleIntegerProperty id_country;
    private SimpleStringProperty country_name;

    public Country(final int id_country,final String country_name) {
        this(country_name);
        this.id_country = new SimpleIntegerProperty(id_country);
    }

    public Country(final String country_name) {
        this.country_name = new SimpleStringProperty(country_name);
    }

    public int getId_country() {
        return id_country.get();
    }

    public SimpleIntegerProperty id_countryProperty() {
        return id_country;
    }

    public void setId_country(int id_country) {
        this.id_country.set(id_country);
    }

    public String getCountry_name() {
        return country_name.get();
    }

    public SimpleStringProperty country_nameProperty() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name.set(country_name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id_country=" + id_country +
                ", country_name=" + country_name +
                '}';
    }
}
