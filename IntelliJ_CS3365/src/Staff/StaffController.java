/*
    Sets up first interface that the staff uses to do all of their functions, and creates the buttons and fields needed
    for the requirements.
 */

package Staff;

import datbaseUtil.dbConnect;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class StaffController {

    Connection connection;

    public StaffController() {
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
    private Button makeAppointment;
    @FXML
    private Button changeAppointment;
    @FXML
    private  Button cancelAppointment;
    @FXML
    private Button checkInAppointment;
    @FXML
    private Button processPayment;
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
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Staff/Logout/EnsureStaffLogout.fxml"));
            Staff.Logout.LogoutController logoutController = (Staff.Logout.LogoutController) loader.getController();

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
    @FXML
    public void setMakeAppointment(ActionEvent e){
        try {
            Stage stage = (Stage) this.makeAppointment.getScene().getWindow();
            stage.close();

            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Staff/MakeAppointment/makeApointment.fxml"));
            Staff.MakeAppointment.MakeAppointmentController makeAppointmentController = (Staff.MakeAppointment.MakeAppointmentController) loader.getController();
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Make Appointment");
            updateStage.setResizable(false);
            updateStage.show();

        }catch (Exception makeAppointment1){
            makeAppointment1.printStackTrace();
        }
    }

    public void setChangeAppointment(ActionEvent e){
        try {
            Stage stage = (Stage) this.changeAppointment.getScene().getWindow();
            stage.close();
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Staff/ChangeAppointment/ChangeAppointment.fxml"));
            Staff.MakeAppointment.MakeAppointmentController makeAppointmentController = (Staff.MakeAppointment.MakeAppointmentController) loader.getController();
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Change Appointment");
            updateStage.setResizable(false);
            updateStage.show();

        }catch (Exception makeAppointment1){
            makeAppointment1.printStackTrace();
        }


    }

    @FXML
    public void setCancelAppointment(ActionEvent e){

        /*
        try {
            Stage stage = (Stage) this.cancelAppointment.getScene().getWindow();
            stage.close();
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            Pane root = (Pane) FXMLLoader.(getClass().getResource("/Staff/CancelAppointment/CancelAppointment.fxml").openStream());
            Staff.MakeAppointment.MakeAppointmentController makeAppointmentController = (Staff.MakeAppointment.MakeAppointmentController) loader.getController();
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Cancel Appointment");
            updateStage.setResizable(false);
            updateStage.show();

        }catch (Exception makeAppointment1){
            makeAppointment1.printStackTrace();
        }

         */
    }

    @FXML
    public void setCheckInAppointment(ActionEvent e) {
        try {
            Stage stage = (Stage) this.checkInAppointment.getScene().getWindow();
            stage.close();
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Staff/CheckInPatient/CheckInPatient.fxml"));
            Staff.MakeAppointment.MakeAppointmentController makeAppointmentController = (Staff.MakeAppointment.MakeAppointmentController) loader.getController();
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Check-In Appointment");
            updateStage.setResizable(false);
            updateStage.show();

        } catch (Exception makeAppointment1) {
            makeAppointment1.printStackTrace();
        }
    }
    public void setProcessPayment(ActionEvent e ){
        try {
            Stage stage = (Stage) this.processPayment.getScene().getWindow();
            stage.close();
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            Pane root = (Pane)loader.load(getClass().getResource("/Staff/ProcessPayment/ProcessPayment.fxml").openStream());
            Staff.ProcessPayment.ProcessPaymentController processPaymentController = (Staff.ProcessPayment.ProcessPaymentController) loader.getController();
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Process Payment");
            updateStage.setResizable(false);
            updateStage.show();

        } catch (Exception makeAppointment1) {
            makeAppointment1.printStackTrace();
        }

    }
}
