package sample.Datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private SimpleIntegerProperty id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private Date birthDate;


    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.id = new SimpleIntegerProperty();
    }

    public Person(int id, String firstName, String lastName, Date birthDate) {
        this(firstName, lastName, birthDate);
        this.id = new SimpleIntegerProperty(id);
    }

    public Person(String firstName, String lastName, Date birthDate) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthDate = birthDate;
        this.id = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.getValue();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.getValue();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.getValue();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id +
                ", firstName=" + getFirstName()  +
                ", lastName=" + getLastName()  +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}
