package hospitalSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datbaseUtil.dbConnect;

public class LoginModuleModel {
    Connection connection;

    public LoginModuleModel() {
        try {
            this.connection = dbConnect.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (this.connection == null) {
            System.exit(1);
        }


    }

    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    public boolean isLogin(String user, String pass, String opt) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Login where Username = ? and Password = ? and Title = ? ";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, opt);

            rs = pr.executeQuery();

            //rs check
            boolean boolOne;

            return rs.next();

        } catch (SQLException ex) {
            return false;
        } finally {{
            pr.close();
            rs.close();}
        }
    }
}




