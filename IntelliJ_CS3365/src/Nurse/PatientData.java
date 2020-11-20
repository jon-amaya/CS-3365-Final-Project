package Nurse;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientData {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty weight;
    private final StringProperty height;
    private final StringProperty bloodPressure;
    private final StringProperty visitReason;

    public PatientData (String firstname, String lastname, String weight, String height, String bloodpressure, String reason) {
        this.firstName = new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        this.weight = new SimpleStringProperty(weight);
        this.height = new SimpleStringProperty(height);
        this.bloodPressure = new SimpleStringProperty(bloodpressure);
        this.visitReason = new SimpleStringProperty(reason);
    }

    //Get/Set for firstName value
    public String getFirstName() {
        return firstName.get();
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    //Get/Set for lastName value
    public String getLastName() {
        return lastName.get();
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    //Get/Set for weight value
    public String getWeight() {
        return weight.get();
    }
    public StringProperty weightProperty() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    //Get/Set for height value
    public String getHeight() {
        return height.get();
    }
    public StringProperty heightProperty() {
        return height;
    }
    public void setHeight(String height) {
        this.height.set(height);
    }

    //Get/Set for bloodPressure value
    public String getBloodPressure() {
        return bloodPressure.get();
    }
    public StringProperty bloodPressureProperty() {
        return bloodPressure;
    }
    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure.set(bloodPressure);
    }

    //Get/Set for visitReason value
    public String getVisitReason() {
        return visitReason.get();
    }
    public StringProperty visitReasonProperty() {
        return visitReason;
    }
    public void setVisitReason(String visitReason) {
        this.visitReason.set(visitReason);
    }

}
