/*
    Sets up the patients information and allows for interaction between the users and the patients information
 */

package Patient;

import com.sun.javafx.iio.common.SmoothMinifier;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientData {
    //private final StringProperty firstName;
    //private final StringProperty lastName;
    private final StringProperty weight;
    private final StringProperty height;
    private final StringProperty bloodPressure;
    private final StringProperty visitReason;
    //private final StringProperty ID;
    //private final StringProperty Email;
    //private final StringProperty DOB;

    /*
    public PatientData (String firstname, String lastname, String weight, String height, String bloodpressure, String reason) {
        this.firstName = new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        this.weight = new SimpleStringProperty(weight);
        this.height = new SimpleStringProperty(height);
        this.bloodPressure = new SimpleStringProperty(bloodpressure);
        this.visitReason = new SimpleStringProperty(reason);
    }

     */

    //PatientData constructor for Nurse
    public PatientData (String weight, String height, String bp, String reason) {
        this.weight = new SimpleStringProperty(weight);
        this.height = new SimpleStringProperty(height);
        this.bloodPressure = new SimpleStringProperty(bp);
        this.visitReason = new SimpleStringProperty(reason);
    }

    /*
    //Test PatientData constructor needed for Testfx Controller
    public PatientData(String id, String firstname, String lastname, String email, String dob) {
        this.firstName = new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        this.ID = new SimpleStringProperty(id);
        this.Email = new SimpleStringProperty(email);
        this.DOB = new SimpleStringProperty(dob);
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
    */


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



/*
    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getEmail() {
        return Email.get();
    }

    public StringProperty emailProperty() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email.set(email);
    }

    public String getDOB() {
        return DOB.get();
    }

    public StringProperty DOBProperty() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }

 */
}
