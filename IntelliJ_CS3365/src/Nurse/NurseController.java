package Nurse;

import Nurse.patientMeasures.PatientMeasureController;
import datbaseUtil.dbConnect;
import hospitalSystem.LogoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
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
    private Button patientMeasureButton;

    @FXML
    private TextField patientFname;

    @FXML
    private TextField patientLname;

    @FXML
    private Label lookupStatus;

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

    //Action function for updatePatientMeasure button
    @FXML
    public void updatePatientMeasure(ActionEvent event) {
        try {

            if(this.checkPatientInfo(this.patientFname.getText(), this.patientLname.getText())) {
                Stage stage = (Stage)this.patientMeasureButton.getScene().getWindow();
                stage.close();

                //call to open patient measurement window
                openPatientMeasures();

            } else {
                this.lookupStatus.setText("Wrong patient information");
            }

        } catch (Exception ex)  {
            ex.printStackTrace();
        }
    }

    //method to open updatePatientMeasure window
    public void openPatientMeasures() {
        try {
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Nurse/patientMeasures/PatientMeasureFXML.fxml"));
            PatientMeasureController patientMeasureController = (PatientMeasureController) loader.getController();


            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Update patient measurements/reason");
            updateStage.setResizable(false);
            updateStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Function for logout button
    @FXML
    public void isLogout(ActionEvent event) {
        try {

            //Open EnsureLogout.fxml
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/hospitalSystem/EnsureLogout.fxml"));
            LogoutController logoutController = (LogoutController) loader.getController();


            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Logout");
            updateStage.setResizable(false);
            updateStage.show();


        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    @FXML
    public void closeWindow() {
        //Comment next two lines to keep nurse window open
        Stage stage = (Stage)this.logoutButton.getScene().getWindow();
        stage.close();
    }

}
