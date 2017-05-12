package inventory.purchase;

import inventory.DbConnection;
import inventory.stock.StockDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

/**
 * Created by Owner on 2017-05-02.
 */
public class Purchase {

    public static int addPurchase(String itemCode, String supplierId, LocalDate purchaseDate, int quantity) throws SQLException, ClassNotFoundException {

        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement stmt = con.prepareStatement("insert into purchase values (?,?,?,?)");
        stmt.setString(1,itemCode);
        stmt.setString(2,supplierId);
        stmt.setString(3, String.valueOf(purchaseDate));
        stmt.setInt(4,quantity);

        PreparedStatement stmt2 = con.prepareStatement("update stock set quantity = quantity + ? where itemCode = ?");
        stmt2.setInt(1,quantity);
        stmt2.setString(2,itemCode);

        int result = stmt.executeUpdate();
        int result2 = stmt2.executeUpdate();

        DbConnection.closeConnection();
        return result;
    }

    public static ObservableList<PurchaseDetail> getPurchase() throws SQLException, ClassNotFoundException {

        ObservableList<PurchaseDetail> purchase = FXCollections.observableArrayList();

        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from purchase order by purchaseDate");

        while (rs.next()) {
            String iCode = rs.getString("itemCode");
            String supId = rs.getString("supplierId");
            int qty = rs.getInt("purchaseQty");
            String date = rs.getString("purchaseDate");
            purchase.add(new PurchaseDetail(iCode,supId,qty,date));
        }

        DbConnection.closeConnection();
        return purchase;
    }



    public static void searchPurchaseByItemCode(String iCode) throws SQLException, ClassNotFoundException {

    }

    public static void searchPurchaseBySupplierId(String supId) throws SQLException, ClassNotFoundException{

    }

    public static void searchPurchaseByDate(String date) throws SQLException, ClassNotFoundException{

    }
}
