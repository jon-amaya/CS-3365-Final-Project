package hospitalSystem;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
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
    public void initialize(URL url , ResourceBundle rb){
        if(this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Connected to Database");
        }else{
            this.dbstatus.setText("Not Connected To Database");
        }

        this.combobox.setItems(FXCollections.observableArrayList(Option.values()));

    }
    @FXML
    public void Login(ActionEvent event){


    }

    public void staffLogin(){

    }

    public  void NurseLogin(){

    }

    public void doctorLogin(){

    }
    
    public void CEOLogin(){

    }
}
