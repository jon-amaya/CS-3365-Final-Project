package Doctor;

import datbaseUtil.dbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorController {

    Connection connection;

    public DoctorController() {
        try {
            this.connection = dbConnect.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }

    @FXML
    private Button logoutButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button updateTreatmentButton;
    @FXML
    private Button clearAll;

    @FXML
    private TextField patientFname;
    @FXML
    private TextField patientLname;
    @FXML
    private TextField currWeight;
    @FXML
    private TextField currHeight;
    @FXML
    private TextField currBP;
    @FXML
    private  TextField currReason;
    @FXML
    private TextField treatmentInfo;
    @FXML
    private TextField prescInfo;

    @FXML
    private Label lookupStatus;
    @FXML
    private Label clearStatus;
    @FXML
    private Label treatmentStatus;

    /**************************************************************************/
    //Method to check entered patient info
    public boolean checkPatientInfo(String fname, String lname) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Patients where Fname = ? and Lname = ?";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, fname);
            pr.setString(2, lname);

            rs = pr.executeQuery();

            boolean bool1;

            return rs.next();

        } catch (SQLException ex) {
            return false;
        } finally {{
            pr.close();
            rs.close();
        }}
    }

    //Method for when search button for patient look up
    @FXML
    public void loadPatientInfoEvent(ActionEvent event) {
        try {

            if(this.checkPatientInfo(this.patientFname.getText(), this.patientLname.getText())) {

                //Method call to load patient data from database
                loadPatientData(this.patientFname.getText());

            } else {
                this.lookupStatus.setText("Wrong patient information");
            }

        } catch (Exception ex)  {
            ex.printStackTrace();
        }

        this.clearStatus.setText("");
        this.treatmentStatus.setText("");
    }

    //Method to load patient information on the screen
    public void loadPatientData(String firstname) {
        String sql = "SELECT Weight, Height, BloodPressure, Reason FROM Patients WHERE Fname = ?";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, firstname);

            connection = dbConnect.getConnection();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                this.currWeight.setText(rs.getString("Weight"));
                this.currHeight.setText(rs.getString("Height"));
                this.currBP.setText(rs.getString("BloodPressure"));
                this.currReason.setText(rs.getString("Reason"));

            }

            ps.close();
            rs.close();

        } catch(SQLException e) {
            System.err.println("Error: "+ e);
        }

    }

    /*************************************************************************/
    //Methods for updating patient treatment and prescription information

    @FXML
    public void addTreatmentInfoEvent(ActionEvent event) {

        try {
            updatePatientInfo(this.patientFname.getText(), this.treatmentInfo.getText(), this.prescInfo.getText());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.treatmentStatus.setText("Patient information added to database");
        this.clearStatus.setText("");
    }

    public void updatePatientInfo(String firstname, String treatment, String presc) {
        String sql = "UPDATE Patients SET Treatment = ?, Prescription = ? WHERE Fname = ?";
        //try statement to update content
        try {


            Connection connection = dbConnect.getConnection();
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);


            ps.setString(1, treatment);
            ps.setString(2, presc);
            ps.setString(3, firstname);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.err.println("Error: "+e);
        }
    }


    /**************************************************************************/
    //Method for clearing all fields
    @FXML
    public void clearFields() {
        this.patientFname.setText("");
        this.patientLname.setText("");
        this.currWeight.setText("");
        this.currHeight.setText("");
        this.currBP.setText("");
        this.currReason.setText("");
        this.treatmentInfo.setText("");
        this.prescInfo.setText("");

        this.lookupStatus.setText("");
        this.treatmentStatus.setText("");
        this.clearStatus.setText("All fields cleared...");
    }

    //Function for logout button
    @FXML
    public void isLogout(ActionEvent event) {
        try {
            //Comment next two lines to keep nurse window open
            Stage stage = (Stage)this.logoutButton.getScene().getWindow();
            stage.close();

            //Open EnsureLogout.fxml
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            //Change the loader FXML file and LogoutController locations for correct employee
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Doctor/Logout/EnsureDoctorLogout.fxml"));
            Doctor.Logout.LogoutController logoutController = (Doctor.Logout.LogoutController) loader.getController();

            //Create new scene with set title and not resizable
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Logout");
            updateStage.setResizable(false);
            updateStage.show();

        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
