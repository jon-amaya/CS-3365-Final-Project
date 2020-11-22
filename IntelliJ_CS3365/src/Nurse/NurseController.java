package Nurse;

import datbaseUtil.dbConnect;
import javafx.collections.ObservableList;
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

public class NurseController {
    Connection connection;

    public NurseController() {
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
    private Button patientCheckButton;
    @FXML
    private Button updatePatientInfoButton;
    @FXML
    private Button clearPatientInfoButton;
    @FXML
    private Button clearAllButton;

    @FXML
    private TextField patientFname;

    @FXML
    private TextField patientLname;

    @FXML
    private Label lookupStatus;

    @FXML
    private Label clearStatus;

    @FXML
    private Label dbUpdateStatus;

    @FXML
    private TextField currWeight;
    @FXML
    private TextField newWeight;

    @FXML
    private TextField currHeight;
    @FXML
    private TextField newHeight;

    @FXML
    private TextField currBP;
    @FXML
    private TextField newBP;

    @FXML
    private TextField currReason;
    @FXML
    private TextField newReason;

    @FXML
    private Button logoutButton;


    //Check the entered patient info from text fields
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

    /***********************************************************************************/
    /* Methods for button press and loading patient data */
    //Action function for updatePatientMeasure button
    @FXML
    public void checkPatientMeasure(ActionEvent event) {
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
    }

    //Function to load patient data into window
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

    /*****************************************************************************/
    /* Methods for add patient info button and to add to database */
    //Method for addPatientInfo button
    @FXML
    public void addPatientInfoEvent(ActionEvent event) {

        try {
            updatePatientInfo(this.newWeight.getText(), this.newHeight.getText(), this.newBP.getText(), this.newReason.getText(), this.patientFname.getText());

        } catch (Exception ex)  {
            ex.printStackTrace();
        }

        this.dbUpdateStatus.setText("Patient information added to database");
    }

    public void updatePatientInfo(String weight, String height, String bloodpressure, String reason, String firstname) {
        String sql = "UPDATE Patients SET Weight = ?, Height = ?, BloodPressure = ?, Reason = ? WHERE Fname = ?";

        try {

            Connection connection = dbConnect.getConnection();
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);

            if (weight == null) {
                weight = this.currWeight.getText();
            }
            if (height == null) {
                height = this.currHeight.getText();
            }
            if (bloodpressure == null) {
                bloodpressure = this.currBP.getText();
            }
            if(reason == null) {
                reason = this.currReason.getText();
            }

            ps.setString(1, weight);
            ps.setString(2, height);
            ps.setString(3, bloodpressure);
            ps.setString(4, reason);
            ps.setString(5, firstname);

            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error: "+e);
        }
    }
    /*****************************************************************************/
    //Methods for clear fields buttons and to set text for label

    @FXML
    public void clearPatientInfo(ActionEvent event) {
        this.newWeight.setText(null);
        this.newHeight.setText(null);
        this.newBP.setText(null);
        this.newReason.setText(null);

        this.clearStatus.setText("New patient information cleared");
        this.dbUpdateStatus.setText("");
    }

    @FXML
    public void clearAllInfo(ActionEvent event) {
        this.patientFname.setText(null);
        this.patientLname.setText(null);
        this.currWeight.setText(null);
        this.currHeight.setText(null);
        this.currBP.setText(null);
        this.currReason.setText(null);
        this.newWeight.setText(null);
        this.newHeight.setText(null);
        this.newBP.setText(null);
        this.newReason.setText(null);

        this.clearStatus.setText("All patient information cleared");
        this.dbUpdateStatus.setText("");

    }

    /*****************************************************************************/
    /* Method calls for logout button */
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
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Nurse/Logout/EnsureNurseLogout.fxml"));
            Nurse.Logout.LogoutController logoutController = (Nurse.Logout.LogoutController) loader.getController();

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
