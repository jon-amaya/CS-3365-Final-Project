package Doctor.Logout;

import hospitalSystem.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;

public class LogoutController {
    Connection connection;
    LoginController loginController = new LoginController();

    public LogoutController() {
    }

    @FXML
    private Button cancelLogout;

    @FXML
    private Button continueLogout;

    @FXML
    public void defsLogout(ActionEvent event) {
        try{
            //Close window and continue
            Stage stage =(Stage)this.continueLogout.getScene().getWindow();
            stage.close();

            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("/hospitalSystem/login.fxml"));
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Hospital Management System");
            updateStage.show();

        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    @FXML
    public void notLogout(ActionEvent event) {
        try{
            //Close window and continue
            Stage stage =(Stage)this.cancelLogout.getScene().getWindow();
            stage.close();

            //Open main nurse window again
            loginController.doctorLogin();


        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

}
