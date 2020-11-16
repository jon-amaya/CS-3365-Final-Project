package datbaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
    private static final String sqliteConnect = "jdbc:sqlite:hospital.sqlite";

    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(sqliteConnect);
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;

    }
}
