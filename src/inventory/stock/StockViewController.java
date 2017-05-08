package inventory.stock;

/**
 * Created by gigar on 30-Apr-17.
 */

import inventory.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
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
    private void goStockDetails() throws IOException {
        main.showStockDetailStage();
    }

    @FXML
    private void doDeleteStock() throws SQLException, ClassNotFoundException {
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

    @FXML
    private void doReturnStock() throws SQLException, ClassNotFoundException {
        int result = Stock.returnStock(Integer.parseInt(returnQty.getText()), returnCode.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Return Stock");
        alert.setHeaderText(null);

        if (result == 1) {
            alert.setContentText("Operation Successful!");
        } else {
            alert.setContentText("Operation Failed");
        }

        alert.show();
        returnCode.clear();
        returnQty.clear();
    }

    @FXML
    private void doUpdateDetail() throws SQLException, ClassNotFoundException {
        int result;

        if (updateName.getText().trim().equals("") && updatePrice.getText().trim().equals("")) {
            result = Stock.updateStock(updateCode.getText(),Integer.parseInt(updateROL.getText()));
        } else if (updateROL.getText().trim().equals("") && updatePrice.getText().trim().equals("") ) {
            result = Stock.updateStock(updateName.getText(),updateCode.getText());
        } else if (updateROL.getText().trim().equals("") && updateName.getText().trim().equals("") ) {
            result = Stock.updateStock(updateCode.getText(), new BigDecimal(updatePrice.getText()));
        } else if (updateName.getText().trim().equals("")) {
            result = Stock.updateStock(updateCode.getText(),Integer.parseInt(updateROL.getText()),new BigDecimal(updatePrice.getText()));
        } else if (updateROL.getText().trim().equals("")) {
            result = Stock.updateStock(updateName.getText(),updateCode.getText(),new BigDecimal(updatePrice.getText()));
        } else if (updatePrice.getText().trim().equals("")) {
            result = Stock.updateStock(updateName.getText(),Integer.parseInt(updateROL.getText()));
        }
        else {
            result = Stock.updateStock(updateName.getText(),updateCode.getText(),Integer.parseInt(updateROL.getText()));
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Return Stock");
        alert.setHeaderText(null);

        if (result == 1) {
            alert.setContentText("Operation Successful!");
        } else {
            alert.setContentText("Operation Failed");
        }

        alert.show();
        updateROL.clear();
        updateName.clear();
        updateCode.clear();
        updatePrice.clear();
    }

}
