package hospitalSystem;

import Nurse.NurseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    NurseController nurseController = new NurseController();
    private String currEmployee;

    public LogoutController() {
    }

    public String getCurrEmployee() {
        return currEmployee;
    }

    public void setCurrEmployee(String currEmployee) {
        this.currEmployee = currEmployee;
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

            //Try to close windows
            this.nurseController.closeWindow();

            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("login.fxml"));
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
            String title = this.getCurrEmployee();
            System.out.println(title);

            switch(title){
                case "Nurse":
                    this.loginController.nurseLogin();
                    break;
                case "Doctor":
                    this.loginController.doctorLogin();
                    break;
            }



        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
