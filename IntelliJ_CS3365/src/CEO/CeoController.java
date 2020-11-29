package CEO;

import datbaseUtil.dbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CeoController {

    Connection connection;

    public CeoController() {
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
    private Button reportButton;

    //@FXML
    //private TextField isReportReady;


    //Function for viewReport Button
    @FXML
    public void viewReportButton(ActionEvent event) {
        try {
            //Open ViewReport.fxml
            Stage ReportStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/CEO/Report/ViewReport.fxml"));
            CEO.Report.ReportController reportController = (CEO.Report.ReportController) loader.getController();

            Scene scene = new Scene(root);
            ReportStage.setScene(scene);
            ReportStage.setTitle("Report Menu");
            ReportStage.setResizable(false);
            ReportStage.show();

        } catch (Exception localException) {
            localException.printStackTrace();
        }
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
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/CEO/Logout/EnsureCEOLogout.fxml"));
            CEO.Logout.LogoutController logoutController = (CEO.Logout.LogoutController) loader.getController();

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
