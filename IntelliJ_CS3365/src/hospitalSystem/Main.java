/*
    Main controller of the system. Starts the main login page and runs the system to allow access to all other
    interactions between users.
 */

package hospitalSystem;

import  javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.stage.Stage;

public class Main extends Application {
    public void start (Stage stage) throws Exception{
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hospital Management System");
        stage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}
