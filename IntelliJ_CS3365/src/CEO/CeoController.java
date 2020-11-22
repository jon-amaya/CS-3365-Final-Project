package CEO;

import datbaseUtil.dbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
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
