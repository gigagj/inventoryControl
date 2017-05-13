package inventory;

import inventory.purchase.PurchaseDetail;
import inventory.sales.SalesDetails;
import inventory.stock.StockDetail;
import inventory.supplier.SupplierDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gigar on 13-May-17.
 */
public class Search {

     /*   public static ObservableList<> SearchDB(String table, String type, String value) throws SQLException, ClassNotFoundException {
        type = type.trim().toLowerCase();

        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();

        PreparedStatement stmt = con.prepareStatement("select * from ? where ? LIKE '%?%'");
        stmt.setString(1,table);
        stmt.setString(2,type);
        stmt.setString(3,value);

        ResultSet rs = stmt.executeQuery();

        if (table == "stock") {
            ObservableList<StockDetail> stock = FXCollections.observableArrayList();
            String itemCode = rs.getString("itemCode");
            int qty = rs.getInt("quantity");
            String name = rs.getString("name");
            int rol = rs.getInt("reOrderLevel");
            BigDecimal price = rs.getBigDecimal("price");
            stock.add(new StockDetail(itemCode,name,qty,rol,price));
            return stock;
        }
        else if (table == "sales") {
            ObservableList<SalesDetails> sale = FXCollections.observableArrayList();
            String itemCode = rs.getString("itemCode");
            String cusNIC = rs.getString("nic");
            int qty = rs.getInt("salesQty");
            String date = rs.getString("salesDate");

            sale.add(new SalesDetails(itemCode,cusNIC,qty, date ));
            return sale;
        }
        else if (table == "supplier") {
            ObservableList<SupplierDetail> supplier= FXCollections.observableArrayList();
            String supplierCode=rs.getString("supplierID");
            String name =rs.getString("name");
            String address =rs.getString("address");
            int contactNumber=rs.getInt("contactNumber");
            supplier.add(new SupplierDetail(supplierCode,name,address,contactNumber));
        }
        else if (table == "purchase") {
            ObservableList<PurchaseDetail> purchase = FXCollections.observableArrayList();
            String iCode = rs.getString("itemCode");
            String supId = rs.getString("supplierId");
            int qty = rs.getInt("purchaseQty");
            String date = rs.getString("purchaseDate");
            purchase.add(new PurchaseDetail(iCode,supId,qty,date));
        }

    } */
}
