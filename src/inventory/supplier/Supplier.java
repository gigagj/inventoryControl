package inventory.supplier;

import inventory.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Vinush on 5/2/2017.
 */
public class Supplier {
    public static ObservableList<SupplierDetail> getSupplier() throws IOException, ClassNotFoundException, SQLException {
        ObservableList<SupplierDetail> supplier= FXCollections.observableArrayList();

        DbConnection.openConnection();
        Connection con=DbConnection.getConnection();
        Statement stmt =con.createStatement();

        ResultSet rs=stmt.executeQuery("select * from supplier");

        while(rs.next()) {

            String supplierCode=rs.getString("supplierID");
            String name =rs.getString("name");
            String address =rs.getString("address");
            int contactNumber=rs.getInt("contactNumber");
            supplier.add(new SupplierDetail(supplierCode,name,address,contactNumber));

        }
        DbConnection.closeConnection();
        return supplier;
    }

    public static int deleteSupplier(String supplierID) throws SQLException, ClassNotFoundException{
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement stmt=con.prepareStatement("Delete from Supplier where supplierCode=?");
        stmt.setString(1,supplierID);

        int result=stmt.executeUpdate();
        System.out.println(result);

        DbConnection.closeConnection();
        return result;
    }

    public static int addSupplier( String supplierID,String name, String address, int contactNumber) throws SQLException, ClassNotFoundException{
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement stmt=con.prepareStatement("insert into Supplier values(?,?,?,?)");
        stmt.setString(1,supplierID);
        stmt.setString(2,name);
        stmt.setString(3,address);
        stmt.setInt(4,contactNumber);


        int result=stmt.executeUpdate();
        System.out.println(result);

        DbConnection.closeConnection();
        return result;
    }

    public static int updateSupplier(String supplierID,String address,int contactNumber) throws IOException, ClassNotFoundException, SQLException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        PreparedStatement stmt;

        stmt = con.prepareStatement("update supplier set address = ?, contactNumber = ? where supplierID = ?");
        stmt.setString(1, address);
        stmt.setInt(2, contactNumber);
        stmt.setString(3, supplierID);

        int result = stmt.executeUpdate();

        DbConnection.closeConnection();
        return result;
    }

    public static int updateSupplier(String supplierID,String address) throws IOException, ClassNotFoundException, SQLException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        PreparedStatement stmt;

        stmt = con.prepareStatement("update supplier set address = ? where supplierID = ?");
        stmt.setString(1, address);
        stmt.setString(2, supplierID);

        int result = stmt.executeUpdate();

        DbConnection.closeConnection();
        return result;
    }


    public static int updateSupplier(String supplierID,int contactNumber) throws IOException, ClassNotFoundException, SQLException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        PreparedStatement stmt;

        stmt = con.prepareStatement("update supplier set contactNumber = ? where supplierID = ?");
        stmt.setInt(1,contactNumber);
        stmt.setString(2, supplierID);

        int result = stmt.executeUpdate();

        DbConnection.closeConnection();
        return result;
    }



}
