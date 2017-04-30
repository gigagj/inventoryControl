package inventory.stock;

import inventory.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by gigar on 28-Apr-17.
 */
public class Stock {

    public static void getStock() throws SQLException, ClassNotFoundException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from stock");

        while (rs.next()) {
            System.out.println(rs.getString("itemCode"));
            System.out.println(rs.getInt(3));
        }

        DbConnection.closeConnection();
    }
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        getStock();
    }
    public static void deleteStock(){}
    public static void updateStock(){}
    public static void returnStock(){}
}
