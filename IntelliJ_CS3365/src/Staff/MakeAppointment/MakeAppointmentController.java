package Staff.MakeAppointment;


import datbaseUtil.dbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.*;

public class MakeAppointmentController {
    Connection connection;
    DateController dc = new DateController();

    public MakeAppointmentController() {
        try {
            this.connection = dbConnect.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }

    public String employeeid;


    //Doctor Attributes
    @FXML
    private TextField doctorFirstName;
    @FXML
    private TextField doctorLastName;
    @FXML
    private Button searchDoctor;

    //Patient Attributes

    @FXML
    private  TextField patientFirstName;
    @FXML
    private  TextField patientLastName;
    @FXML
    private  TextField doctorFirstNameTwo;
    @FXML
    private TextField doctorLastNameTwo;
    @FXML
    private  TextField date;
    @FXML
    private TextField time;
    @FXML
    private Button submitButton;





    //Method to check entered patient info
    public void checkDoctorInfo(String fname, String lname) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT EmployeeID FROM Staff where FirstName = ? and LastName = ?";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, fname);
            pr.setString(2, lname);

            rs = pr.executeQuery();

            while (rs.next()) {
                this.employeeid = rs.getString("EmployeeID");
            }

        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        } finally {{
            pr.close();
            rs.close();
        }}

    }


    //Method for when search button for patient look up
    @FXML
    public void searchDocAppointment(ActionEvent event) {
        try {

            checkDoctorInfo(this.doctorFirstName.getText(), this.doctorLastName.getText());

            //Open EnsureLogout.fxml
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            //Change the loader FXML file and LogoutController locations for correct employee
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Staff/MakeAppointment/DoctorDate.fxml"));
            Staff.MakeAppointment.DateController controller = (Staff.MakeAppointment.DateController) loader.getController();

            //Create new scene with set title and not resizable
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Logout");
            updateStage.setResizable(false);
            updateStage.show();

            dc.loadAppointmentData();


            //loadAppointmentData(employeeid);

        } catch (Exception ex)  {
            ex.printStackTrace();
        }

    }

    public void loadAppointmentData(String id) {
        //this.m8.setText("titties");

        /*
        String [] times = {"8:00am", "8:30am", "9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm," +
                "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm", "3:00pm", "3:30pm", "4:00pm", "4:30pm"};

        String sqlM = "SELECT Monday FROM Appointments WHERE DoctorID = ? and AppointmentTime = ?";

        PreparedStatement pr = null;
        ResultSet rs = null;

        try {
            pr = this.connection.prepareStatement(sqlM);
            pr.setString(1, id);

            for (int i = 0; i < times.length; i++) {

                pr.setString(2, times[i]);

                rs = pr.executeQuery();

                while(rs.next()) {

                    if (i == 0)
                        this.m8.setText(rs.getString("Monday"));
                    else if (i == 1)
                        this.m83.setText(rs.getString("Monday"));

                        this.m8.setText(rs.getString("Monday"));
                    this.m83.setText(rs.getString("Monday"));
                    this.m9.setText(rs.getString("Monday"));
                    this.m93.setText(rs.getString("Monday"));
                    this.m10.setText(rs.getString("Monday"));
                    this.m103.setText(rs.getString("Monday"));
                    this.m11.setText(rs.getString("Monday"));
                    this.m113.setText(rs.getString("Monday"));
                    this.m12.setText(rs.getString("Monday"));
                    this.m123.setText(rs.getString("Monday"));
                    this.m1.setText(rs.getString("Monday"));
                    this.m13.setText(rs.getString("Monday"));
                    this.m2.setText(rs.getString("Monday"));
                    this.m23.setText(rs.getString("Monday"));
                    this.m3.setText(rs.getString("Monday"));
                    this.m33.setText(rs.getString("Monday"));
                    this.m4.setText(rs.getString("Monday"));
                    this.m43.setText(rs.getString("Monday"));


                }


            }

        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }


         */

    }



}
