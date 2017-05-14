package inventory.sales;

import inventory.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

/**
 * Created by Ranjitha on 5/2/2017.
 */
public class Sales {
    public static ObservableList<SalesDetails> getSales() throws SQLException, ClassNotFoundException {
        ObservableList<SalesDetails> sale = FXCollections.observableArrayList();

        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from sales");

        while (rs.next()) {
            String itemCode = rs.getString("itemCode");
            String cusNIC = rs.getString("nic");
            int qty = rs.getInt("salesQty");
            String date = rs.getString("salesDate");


            sale.add(new SalesDetails(itemCode,cusNIC,qty, date ));

        }
        DbConnection.closeConnection();
        return sale;
    }
        public static int addSales(String itemCode, String nic, int quantity, LocalDate date) throws SQLException, ClassNotFoundException {
            DbConnection.openConnection();
            Connection con = DbConnection.getConnection();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            PreparedStatement stmt = con.prepareStatement("insert into sales values (?,?,?,?)");
            stmt.setString(1,itemCode);
            stmt.setString(2,nic);
            stmt.setString(3,String.valueOf(date));
            stmt.setInt(4,quantity);


            int result = -1;

            try {
                result = stmt.executeUpdate();
            } catch (Exception e) {
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }


            DbConnection.closeConnection();
            return result;


        }
}


