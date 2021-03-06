package hospitalSystem;

import CEO.CeoController;
import Doctor.DoctorController;
import Nurse.NurseController;
import Staff.StaffController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModuleModel loginModel = new LoginModuleModel();
    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginstatus;


    public void initialize(URL url , ResourceBundle rb){
        if(this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Connected to Database");
        }else{
            this.dbstatus.setText("Not Connected To Database");
        }

        this.combobox.setItems(FXCollections.observableArrayList(Option.values()));

    }

    @FXML
    public void Login (ActionEvent event){
        try{
            if(this.loginModel.isLogin(this.username.getText(),this.password.getText(),((Option)this.combobox.getValue()).toString())){
                Stage stage =(Stage)this.loginButton.getScene().getWindow();
                stage.close();
                switch (((Option)this.combobox.getValue()).toString()){
                    case "Staff":
                        staffLogin();;
                        break;
                    case "Doctor":
                        doctorLogin();
                        break;
                    case "Nurse":
                        nurseLogin();
                        break;
                    case "CEO":
                        ceoLogin();
                        break;
                }
            }else {
                this.loginstatus.setText("Wrong Credentials");
            }

        }
        catch (Exception localException){
            localException.printStackTrace();

        }

    }

    public void staffLogin(){
        try{
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/Staff/staffXML.fxml").openStream());
            StaffController staffController = (StaffController)loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Staff Menu");
            userStage.setResizable(false);
            userStage.show();


        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void nurseLogin(){
        try{
            Stage nurseStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/Nurse/NurseFXML.fxml").openStream());
            NurseController nurseController = (NurseController)loader.getController();

            Scene scene = new Scene(root);
            nurseStage.setScene(scene);
            nurseStage.setTitle("Nurse Menu");
            nurseStage.setResizable(false);
            nurseStage.show();


        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void doctorLogin(){
        try{
            Stage doctorStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/Doctor/DoctorFXML.fxml").openStream());
            DoctorController doctorController = (DoctorController)loader.getController();

            Scene scene = new Scene(root);
            doctorStage.setScene(scene);
            doctorStage.setTitle("Doctor Menu");
            doctorStage.setResizable(false);
            doctorStage.show();


        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    //Function for CEO login
    public void ceoLogin(){
        try{
            Stage ceoStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/CEO/CeoFXML.fxml").openStream());
            CeoController ceoController = (CeoController)loader.getController();

            Scene scene = new Scene(root);
            ceoStage.setScene(scene);
            ceoStage.setTitle("CEO Menu");
            ceoStage.setResizable(false);
            ceoStage.show();


        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

}
