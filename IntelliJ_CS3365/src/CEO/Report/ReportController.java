/*
    Allows for retrieving the daily report if logged in as the CEO and viewing the report
 */

package CEO.Report;

import datbaseUtil.dbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportController {

    Connection connection;


    public ReportController() {
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
    private Button Done;
    @FXML
    private Button Load;

    @FXML
    private Label doctor1;
    @FXML
    private Label doctor2;
    @FXML
    private Label doctor3;
    @FXML
    private Label doctor4;
    @FXML
    private Label doctor5;

    @FXML
    private Label doctorPatients1;
    @FXML
    private Label doctorPatients2;
    @FXML
    private Label doctorPatients3;
    @FXML
    private Label doctorPatients4;
    @FXML
    private Label doctorPatients5;

    @FXML
    private Label doctorEarned1;
    @FXML
    private Label doctorEarned2;
    @FXML
    private Label doctorEarned3;
    @FXML
    private Label doctorEarned4;
    @FXML
    private Label doctorEarned5;

    @FXML
    private Label totalEarned;
    @FXML
    private Label totalPatients;

    @FXML
    public void loadReport(ActionEvent event) {
        try {
            this.doctor1.setText("629426");
            this.doctor2.setText("748792");
            this.doctor3.setText("026417");
            this.doctor4.setText("216982");
            this.doctor5.setText("814665");

            String[] doctorIDArray = {"629426","748792","026417","216982","814665"};
            String Date = "1/23/20";
            String sql = "SELECT NumPatients, AmountEarned FROM DailyReport WHERE Date = ? and DoctorID = ?";
            int totalAmountInt = 0;
            int totalPatientInt = 0;

            PreparedStatement pr = null;
            ResultSet rs = null;

            for(int i = 0; i <= 4; i++)
            {
                pr = this.connection.prepareStatement(sql);
                pr.setString(1,Date);
                pr.setString(2,doctorIDArray[i]);

                rs = pr.executeQuery();

                while(rs.next()) {
                    // Doctor 1
                    if(i == 0)
                    {
                        this.doctorPatients1.setText(rs.getString("NumPatients"));
                        this.doctorEarned1.setText(rs.getString("AmountEarned"));
                        totalAmountInt = totalAmountInt + Integer.parseInt(rs.getString("AmountEarned"));
                        totalPatientInt = totalPatientInt + Integer.parseInt(rs.getString("NumPatients"));
                    }
                    // Doctor 2
                    else if(i == 1)
                    {
                        this.doctorPatients2.setText(rs.getString("NumPatients"));
                        this.doctorEarned2.setText(rs.getString("AmountEarned"));
                        totalAmountInt = totalAmountInt + Integer.parseInt(rs.getString("AmountEarned"));
                        totalPatientInt = totalPatientInt + Integer.parseInt(rs.getString("NumPatients"));
                    }
                    // Doctor 3
                    else if(i == 2)
                    {
                        this.doctorPatients3.setText(rs.getString("NumPatients"));
                        this.doctorEarned3.setText(rs.getString("AmountEarned"));
                        totalAmountInt = totalAmountInt + Integer.parseInt(rs.getString("AmountEarned"));
                        totalPatientInt = totalPatientInt + Integer.parseInt(rs.getString("NumPatients"));
                    }
                    // Doctor 4
                    else if(i == 3)
                    {
                        this.doctorPatients4.setText(rs.getString("NumPatients"));
                        this.doctorEarned4.setText(rs.getString("AmountEarned"));
                        totalAmountInt = totalAmountInt + Integer.parseInt(rs.getString("AmountEarned"));
                        totalPatientInt = totalPatientInt + Integer.parseInt(rs.getString("NumPatients"));
                    }
                    // Doctor 5
                    else if(i == 4)
                    {
                        this.doctorPatients5.setText(rs.getString("NumPatients"));
                        this.doctorEarned5.setText(rs.getString("AmountEarned"));
                        totalAmountInt = totalAmountInt + Integer.parseInt(rs.getString("AmountEarned"));
                        totalPatientInt = totalPatientInt + Integer.parseInt(rs.getString("NumPatients"));
                    }
                }

                // Find total amount of Patients/Amount Earned
                String totalPatientString = Integer.toString(totalPatientInt);
                String totalAmountString = Integer.toString(totalAmountInt);
                this.totalPatients.setText(totalPatientString + " Patients");
                this.totalEarned.setText("$" + totalAmountString);

                pr.close();
                rs.close();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @FXML
    public void doneReport(ActionEvent event)
    {
        try{
            // Close window and continue
            Stage stage = (Stage)this.Done.getScene().getWindow();
            stage.close();

            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("/CEO/CeoFXML.fxml"));
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
