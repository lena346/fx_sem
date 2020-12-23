package sample.models;

import javafx.beans.property.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Locale;

public class Person {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthdate;

    public Person(){
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("lastName");
        this.street = new SimpleStringProperty("");
        this.postalCode = new SimpleIntegerProperty(0);
        this.city = new SimpleStringProperty("какой- то город");
        this.birthdate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1995,1,1));



    }



    public Person(String firstName, String lastName){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        //фиктивные данные

        this.street = new SimpleStringProperty("какая-то улицы");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("какой-то город");
        this.birthdate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1995,1,1));


    }

    public String getFirstName() {
        return firstName.get();
    }
    public StringProperty getFirstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }
    public StringProperty getLastNameProperty() {
        return lastName;
    }

    public Integer getPostalCode() {
        return postalCode.get();
    }

    public String getStreet() {
        return street.get();
    }

    public Object getBirthdate() {
        return birthdate.get();
    }

    public String getCity() {
        return city.get();
    }
    public void setStreet(String street){
        this.street.set(street);
    }
    public void setCity(String city){
        this.city.set(city);
    }
    public void setFirstName(String firstName){
        this.firstName.set(firstName);
    }
    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }
    public void setPostalCode(int postalCode){
        this.postalCode.set(postalCode);
    }

}
