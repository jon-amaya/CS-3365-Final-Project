package Nurse;

import Patient.PatientData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import datbaseUtil.dbConnect;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestfxController implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private TextField firstname;

    @FXML
    private  TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private DatePicker dob;

    @FXML
    private TableView<PatientData> patientTable;

    @FXML
    private TableColumn<PatientData, String> idcolumn;

    @FXML
    private  TableColumn<PatientData, String> fnamecolumn;

    @FXML
    private  TableColumn<PatientData, String> lnamecolumn;

    @FXML
    private  TableColumn<PatientData, String> emailcolumn;

    @FXML
    private  TableColumn<PatientData, String> dobcolumn;

    //Create database connection
    private dbConnect dc;

    //Creates arraylist of patient data
    private ObservableList<PatientData> data;



    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnect();

    }

    @FXML
    private void loadPatientData(ActionEvent event)  {
        try {
            //Create sql query string
            String sql = "SELECT * FROM Patients";
            //SELECT ID, Fname, Lname, Email, DOB

            Connection connection = dbConnect.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = connection.createStatement().executeQuery(sql);

            //Loads data from database until all is gotten
            while(rs.next()) {
                //rs.getString(n) where n is the column index in the Patients table
                //this.data.add(new PatientData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(14)));
            }


        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }

        //Methods to set text for cells of table
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("ID"));
        this.fnamecolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("firstName"));
        this.lnamecolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("lastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("Email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<PatientData, String>("DOB"));

        this.patientTable.setItems(null);
        this.patientTable.setItems(this.data);

    }

    @FXML
    private void addPatientData(ActionEvent event){
        //Create sql query string to add data to database
        String sql = "INSERT INTO Patients(id, Fname, Lname, Email, DOB) VALUES (?,?,?,?,?)";

        try {
            try (Connection connection = dbConnect.getConnection()) {
                assert connection != null;
                PreparedStatement ps = connection.prepareStatement(sql);

                //Calls to get parameters to store.
                ps.setString(1, this.id.getText()); //id is text field in window, and it gets it as text
                ps.setString(2, this.firstname.getText());
                ps.setString(3, this.lastname.getText());
                ps.setString(4, this.email.getText());
                ps.setString(5, this.dob.getEditor().getText()); //Needs .getEditor to get date from date picker

                ps.execute();
                connection.close();
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clearFields(ActionEvent event) {
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.setValue(null);
    }
}
