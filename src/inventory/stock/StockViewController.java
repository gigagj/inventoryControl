package inventory.stock;

/**
 * Created by gigar on 30-Apr-17.
 */

import inventory.DbConnection;
import inventory.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class StockViewController {
    private Main main;

    @FXML
    private TextField codeColumn;

    @FXML
    private TextField returnCode;
    @FXML
    private TextField returnQty;

    @FXML
    private TextField updateCode;
    @FXML
    private TextField updateName;
    @FXML
    private TextField updateROL;
    @FXML
    private TextField updatePrice;

    @FXML
    private void showReport() throws JRException, SQLException, ClassNotFoundException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        String report = "C:\\Users\\gigar\\IdeaProjects\\inventoryControl\\src\\inventory\\stock\\allstock.jrxml";
        JasperReport jr = JasperCompileManager.compileReport(report);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
        JasperViewer.viewReport(jp,false);
        DbConnection.closeConnection();
    }

    @FXML
    private void goStockDetails() throws IOException {
        main.showStockDetailStage();
    }

    @FXML
    private void doDeleteStock() throws SQLException, ClassNotFoundException {
        String s = codeColumn.getText();
        s = s.trim();

        if (s.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Stock");
            alert.setHeaderText(null);
            alert.setContentText("Item Code Field Cannot be Empty!");
            alert.show();
        }
        else {
            int result = Stock.deleteStock(codeColumn.getText());
            codeColumn.clear();
            if (result == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Stock");
                alert.setHeaderText(null);
                alert.setContentText("Item Successfully Deleted!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Stock");
                alert.setHeaderText(null);
                alert.setContentText("Deletion Failed! \n Item Not Found!");
                alert.show();
            }
        }

    }

    @FXML
    private void doReturnStock() throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Return Stock");
        alert.setHeaderText(null);

        String code = returnCode.getText();
        String qty = returnQty.getText();
        code = code.trim();
        qty = qty.trim();

        if (code.isEmpty() && qty.isEmpty()) {
            alert.setContentText("Fields are empty!");
        }
        else if(code.isEmpty()) {
            alert.setContentText("Item Code cannot be empty!");
        } else if (qty.isEmpty()) {
            alert.setContentText("Quantity field is empty!");
        } else {
            int result = Stock.returnStock(Integer.parseInt(returnQty.getText()), returnCode.getText());


            if (result == 1) {
                alert.setContentText("Operation Successful!");
            } else {
                alert.setContentText("Operation Failed");
            }

        }

        alert.show();
        returnCode.clear();
        returnQty.clear();
    }



    @FXML
    private void doUpdateDetail() throws SQLException, ClassNotFoundException {
        int result;
        String code = updateCode.getText().trim();
        String price = updatePrice.getText().trim();
        String name = updateName.getText().trim();
        String rol = updateROL.getText().trim();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Return Stock");
        alert.setHeaderText(null);

        if (code.isEmpty() && price.isEmpty() && name.isEmpty() && rol.isEmpty()) {
            alert.setContentText("All Fields cannot be empty");
        } else if (price.isEmpty() && name.isEmpty() && rol.isEmpty() && !code.isEmpty()) {
            alert.setContentText("You have to enter atleast one field to update details!");
        } else if (code.isEmpty()) {
            alert.setContentText("Item Code cannot be empty!");
        } else {

            if (updateName.getText().trim().equals("") && updatePrice.getText().trim().equals("")) {
                result = Stock.updateStock(updateCode.getText(), Integer.parseInt(updateROL.getText()));
            } else if (updateROL.getText().trim().equals("") && updatePrice.getText().trim().equals("")) {
                result = Stock.updateStock(updateName.getText(), updateCode.getText());
            } else if (updateROL.getText().trim().equals("") && updateName.getText().trim().equals("")) {
                result = Stock.updateStock(updateCode.getText(), new BigDecimal(updatePrice.getText()));
            } else if (updateName.getText().trim().equals("")) {
                result = Stock.updateStock(updateCode.getText(), Integer.parseInt(updateROL.getText()), new BigDecimal(updatePrice.getText()));
            } else if (updateROL.getText().trim().equals("")) {
                result = Stock.updateStock(updateName.getText(), updateCode.getText(), new BigDecimal(updatePrice.getText()));
            } else if (updatePrice.getText().trim().equals("")) {
                result = Stock.updateStock(updateName.getText(), Integer.parseInt(updateROL.getText()));
            } else {
                result = Stock.updateStock(updateName.getText(), updateCode.getText(), Integer.parseInt(updateROL.getText()));
            }


            if (result == 1) {
                alert.setContentText("Operation Successful!");
            } else {
                alert.setContentText("Operation Failed");
            }

        }

        alert.show();
        updateROL.clear();
        updateName.clear();
        updateCode.clear();
        updatePrice.clear();
    }
}
