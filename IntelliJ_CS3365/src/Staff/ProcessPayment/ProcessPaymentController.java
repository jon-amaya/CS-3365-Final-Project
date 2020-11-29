/*
    Creates the interface for taking payments from the patient.
 */

package Staff.ProcessPayment;

import datbaseUtil.dbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessPaymentController {

    Connection connection;

    public ProcessPaymentController() {
        try {
            this.connection = dbConnect.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }

    public String fname;
    public String lname;


    @FXML
    private TextField patientFname;
    @FXML
    private TextField patientLname;
    @FXML
    private Label searchStatus;
    @FXML
    private Button searchButton;
    @FXML
    private Button cashButton;
    @FXML
    private Button debitButton;
    @FXML
    private Button creditButton;
    @FXML
    private Label paymentAmount;
    @FXML
    private TextField paidAmount;
    @FXML
    private  Label changeAmount;
    @FXML
    private Button changeButton;


    /******************************************************************************/

    //Check the entered patient info from text fields
    public boolean checkPatientInfo(String fname, String lname) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Patients where Fname = ? and Lname = ?";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, fname);
            pr.setString(2, lname);

            rs = pr.executeQuery();

            boolean bool1;

            return rs.next();

        } catch (SQLException ex) {
            return false;
        } finally {{
            pr.close();
            rs.close();
        }}
    }

    /* Methods for button press and loading patient data */
    //Action function for updatePatientMeasure button
    @FXML
    public void checkPatientName(ActionEvent event) {
        try {

            if(this.checkPatientInfo(this.patientFname.getText(), this.patientLname.getText())) {

                //setPatientFname(patientFname.getText());

                this.searchStatus.setText("Patient information found; please continue..");

            } else {
                this.searchStatus.setText("Patient information incorrect; please search again..");
                this.patientFname.setText("");
                this.patientLname.setText("");
            }

        } catch (Exception ex)  {
            ex.printStackTrace();
        }
    }

    /********************************************************************************/

    @FXML
    public void setCash(ActionEvent event) {

        try {
            String sql = "SELECT PaymentDue, PaymentAmount FROM Patients WHERE Fname = ? AND Lname = ?";
            String isDue = "";
            String amount = "";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, this.patientFname.getText());
                ps.setString(2, this.patientLname.getText());

                connection = dbConnect.getConnection();

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    isDue = rs.getString("PaymentDue");
                    amount = rs.getString("PaymentAmount");
                }


                if (isDue.equals("Yes")) {
                    this.paymentAmount.setText("$" + amount);
                } else {
                    this.paymentAmount.setText("None; Covered by insurance");
                }

                ps.close();
                rs.close();

            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void getChange(ActionEvent event) {
        try {
            String amount = this.paymentAmount.getText();
            int amountInt = Integer.parseInt(amount);

            String total = this.paidAmount.getText();
            int totalInt = Integer.parseInt(total);

            int changeInt = totalInt - amountInt;
            String change = Integer.toString(changeInt);

            this.changeAmount.setText("$" + change);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /*************************************************************************/



}
