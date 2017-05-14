package inventory.stock;

import inventory.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Created by gigar on 28-Apr-17.
 */
public class Stock {

    public static int addStock(String iCode,String name,int quantity,int rol,BigDecimal price) throws SQLException, ClassNotFoundException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement stmt = con.prepareStatement("insert into stock values (?,?,?,?,?)");

        stmt.setString(1, iCode);
        stmt.setString(2, name);
        stmt.setInt(3, quantity);
        stmt.setInt(4, rol);
        stmt.setBigDecimal(5, price);

        int result = stmt.executeUpdate();
        DbConnection.closeConnection();

        return result;
    }

    public static ObservableList<StockDetail> getStock() throws SQLException, ClassNotFoundException {
        ObservableList<StockDetail> stock = FXCollections.observableArrayList();

        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from stock");

        while (rs.next()) {
            String itemCode = rs.getString("itemCode");
            int qty = rs.getInt("quantity");
            String name = rs.getString("name");
            int rol = rs.getInt("reOrderLevel");
            BigDecimal price = rs.getBigDecimal("price");
            stock.add(new StockDetail(itemCode,name,qty,rol,price));

        }

        DbConnection.closeConnection();
        return stock;
    }

    public static int deleteStock(String itemCode) throws SQLException, ClassNotFoundException {
       int result = -1;
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement stmt = con.prepareStatement("delete from stock where itemCode=?");
        stmt.setString(1,itemCode);

        try {
            result = stmt.executeUpdate();
        } catch (Exception e) {
            alert2.setTitle("Error");
            alert2.setHeaderText(null);
            alert2.setContentText(e.getMessage());
            alert2.show();
        }

        DbConnection.closeConnection();
        return result;

    }

    public static int updateStock(String itemCode, int ROL) throws SQLException, ClassNotFoundException {
        return updateStock(null,itemCode,ROL,null);
    }

    public static int updateStock(String name, String itemCode) throws SQLException, ClassNotFoundException {
        return updateStock(name,itemCode,-1,null);
    }

    public static int updateStock(String itemCode, BigDecimal price) throws SQLException, ClassNotFoundException {
        return updateStock(null,itemCode,-1,price);
    }

    public static int updateStock(String itemCode, int ROL, BigDecimal price) throws SQLException, ClassNotFoundException {
        return updateStock(null,itemCode,ROL,price);
    }
    public static int updateStock(String name, String itemCode, BigDecimal price) throws SQLException, ClassNotFoundException {
        return updateStock(name,itemCode,-1,price);
    }

    public static int updateStock(String name, String itemCode, int ROL) throws SQLException, ClassNotFoundException {
        return updateStock(name,itemCode,ROL,null);
    }

    public static int updateStock(String name ,String itemCode, int ROL,BigDecimal price) throws SQLException, ClassNotFoundException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        PreparedStatement stmt;

        if (name == null && price == null) {
            stmt = con.prepareStatement("update stock set reOrderLevel = ? where itemCode = ?");
            stmt.setInt(1,ROL);
            stmt.setString(2,itemCode);
        } else if (ROL == -1 && price == null) {
            stmt = con.prepareStatement("update stock set name = ? where itemCode = ?");
            stmt.setString(1,name);
            stmt.setString(2,itemCode);
        } else if (name == null && ROL == -1) {
            stmt = con.prepareStatement("update stock set price = ? where itemCode = ?");
            stmt.setBigDecimal(1,price);
            stmt.setString(2,itemCode);
        } else if (name == null) {
            stmt = con.prepareStatement("update stock set reOrderLevel = ?, price = ? where itemCode = ?");
            stmt.setInt(1,ROL);
            stmt.setBigDecimal(2,price);
            stmt.setString(3,itemCode);
        } else if (ROL == -1) {
            stmt = con.prepareStatement("update stock set name = ?, price = ? where itemCode = ?");
            stmt.setString(1,name);
            stmt.setBigDecimal(2,price);
            stmt.setString(3,itemCode);
        } else if (price == null) {
            stmt = con.prepareStatement("update stock set name = ?, reOrderLevel = ? where itemCode = ?");
            stmt.setString(1,name);
            stmt.setInt(2,ROL);
            stmt.setString(3,itemCode);
        }
        else {
            stmt = con.prepareStatement("update stock set name = ? , reOrderLevel = ?, price = ? where itemCode = ?");
            stmt.setString(1,name);
            stmt.setInt(2,ROL);
            stmt.setBigDecimal(3,price);
            stmt.setString(4,itemCode);
        }

        int result = stmt.executeUpdate();

        DbConnection.closeConnection();
        return result;
    }

    public static int returnStock(int qty, String itemCode) throws SQLException, ClassNotFoundException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement stmt = con.prepareStatement("update stock set quantity = quantity - ? where itemCode = ?");
        stmt.setInt(1,qty);
        stmt.setString(2,itemCode);

        int result = stmt.executeUpdate();
        System.out.println(result);

        DbConnection.closeConnection();
        return result;
    }


}
