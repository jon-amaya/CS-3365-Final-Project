/*
    Sets up the interaction to check the dates for making new appointments.
 */

package Staff.MakeAppointment;

import datbaseUtil.dbConnect;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class DateController {

    Connection connection;

    public DateController() {
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
    private TextField m8;
    @FXML
    private TextField m83;
    @FXML
    private TextField m9;
    @FXML
    private TextField m93;
    @FXML
    private TextField m10;
    @FXML
    private TextField m103;
    @FXML
    private TextField m11;
    @FXML
    private TextField m113;
    @FXML
    private TextField m12;
    @FXML
    private TextField m123;
    @FXML
    private TextField m1;
    @FXML
    private TextField m13;
    @FXML
    private TextField m2;
    @FXML
    private TextField m23;
    @FXML
    private TextField m3;
    @FXML
    private TextField m33;
    @FXML
    private TextField m4;
    @FXML
    private TextField m43;

    public void loadAppointmentData() throws Exception {
        this.m8.setText("titties");

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
