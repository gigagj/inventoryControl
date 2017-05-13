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
// creating objects and load it in
        }
        DbConnection.closeConnection();
        return supplier;
    }

    public static int deleteSupplier(String supplierID) throws SQLException, ClassNotFoundException{
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement del=con.prepareStatement("Delete from Supplier where supplierId=?");
        del.setString(1,supplierID);

        int result=del.executeUpdate();
        System.out.println(result);

        DbConnection.closeConnection();
        return result;
    }

    public static int addSupplier( String supplierId,String name, String address, int contactNumber) throws SQLException, ClassNotFoundException{
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement add=con.prepareStatement("insert into Supplier values(?,?,?,?)");
        add.setString(1,supplierId);
        add.setString(2,name);
        add.setString(3,address);
        add.setInt(4,contactNumber);

        /*System.out.println(supplierId);
        System.out.println(name);
        System.out.println(address);
        System.out.println(contactNumber);*/
        int result =0;
        result = add.executeUpdate();
        System.out.println(result);

        DbConnection.closeConnection();
        return result;
    }

    public static int updateSupplier(String supplierID,String address,int contactNumber) throws IOException, ClassNotFoundException, SQLException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        PreparedStatement up;

        up = con.prepareStatement("update supplier set address = ?, contactNumber = ? where supplierID = ?");
        up.setString(1, address);
        up.setInt(2, contactNumber);
        up.setString(3, supplierID);

        int result = up.executeUpdate();

        DbConnection.closeConnection();
        return result;
    }

    public static int updateSupplier(String supplierID,String address) throws IOException, ClassNotFoundException, SQLException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        PreparedStatement up;

        up = con.prepareStatement("update supplier set address = ? where supplierID = ?");
        up.setString(1, address);
        up.setString(2, supplierID);

        int result = up.executeUpdate();

        DbConnection.closeConnection();
        return result;
    }


    public static int updateSupplier(String supplierID,int contactNumber) throws IOException, ClassNotFoundException, SQLException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        PreparedStatement up;

        up = con.prepareStatement("update supplier set contactNumber = ? where supplierID = ?");
        up.setInt(1,contactNumber);
        up.setString(2, supplierID);

        int result = up.executeUpdate();

        DbConnection.closeConnection();
        return result;
    }



}
