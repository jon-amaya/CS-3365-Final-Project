package Nurse.patientMeasures;

import Nurse.NurseController;
import datbaseUtil.dbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PatientMeasureController {

    //Create database connector
    Connection connection;

    public PatientMeasureController() {
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
    private Button returnButton;

    //Method for 'return' button to return back to nurse home screen
    @FXML
    public void isReturn(ActionEvent event) {
        try {
            Stage stage = (Stage) this.returnButton.getScene().getWindow();
            stage.close();

            Stage nurseStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/Nurse/NurseFXML.fxml").openStream());
            NurseController nurseController = (NurseController)loader.getController();

            Scene scene = new Scene(root);
            nurseStage.setScene(scene);
            nurseStage.setTitle("Nurse Menu");
            nurseStage.setResizable(false);
            nurseStage.show();

        } catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
