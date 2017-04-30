package inventory;

/**
 * Created by gigar on 29-Apr-17.
 */

import java.sql.*;

public class DbConnection {
   private static Connection con = null;

   public static void openConnection() throws ClassNotFoundException, SQLException {

       String dbURL = "jdbc:sqlserver://localhost;databaseName=inventory;integratedSecurity=true;";

       try {
           DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver() );
           con = DriverManager.getConnection(dbURL);
           System.out.println("Connected");
       }
       catch (Exception e){
           System.out.println("Error "+ e);
       }


    }

    public static Connection getConnection(){
       return con;
    }
    public static void closeConnection() throws SQLException { con.close();}
}
