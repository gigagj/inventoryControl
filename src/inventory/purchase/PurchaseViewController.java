package inventory.purchase;
import inventory.DbConnection;
import inventory.stock.Stock;
import inventory.Main;
import inventory.purchase.Purchase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.System.currentTimeMillis;

/**
 * Created by Owner on 2017-05-03.
 */
public class PurchaseViewController implements Initializable {

    private Main main;
//add purchase
    @FXML
    private TextField addCode;
    @FXML
    private TextField addSup;
    @FXML
    private DatePicker addDate;
    @FXML
    private TextField addQty;
//add stock
    @FXML
    private TextField stockCode;
    @FXML
    private TextField stockName;
    @FXML
    private TextField stockQty;
    @FXML
    private TextField stockROL;
    @FXML
    private TextField stockPrice;
//view all purchase
    @FXML
    TableView<PurchaseDetail> purTable;
    @FXML
    TableColumn itemCode;
    @FXML
    TableColumn supId;
    @FXML
    TableColumn purDate;
    @FXML
    TableColumn purQty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itemCode.setCellValueFactory(new PropertyValueFactory<PurchaseDetail,String>("itemCode"));
        supId.setCellValueFactory(new PropertyValueFactory<PurchaseDetail,String>("supplierId"));
        purDate.setCellValueFactory(new PropertyValueFactory<PurchaseDetail,String>("purchaseDate"));
        purQty.setCellValueFactory(new PropertyValueFactory<PurchaseDetail,Integer>("quantity"));


        try {
            purTable.setItems(Purchase.getPurchase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void showReportPurchase() throws JRException, SQLException, ClassNotFoundException {
        DbConnection.openConnection();
        Connection con = DbConnection.getConnection();
        String report = "C:\\Users\\gigar\\IdeaProjects\\inventoryControl\\src\\inventory\\purchase\\allpurchase.jrxml";
        JasperReport jr = JasperCompileManager.compileReport(report);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
        JasperViewer.viewReport(jp,false);
        DbConnection.closeConnection();
    }

    @FXML
    private void doAddStock() throws SQLException, ClassNotFoundException {
        int result = 0 ;
        String code = stockCode.getText().trim();
        String name = stockName.getText().trim();
        String qty = stockQty.getText().trim();
        String ROL = stockROL.getText().trim();
        String price = stockPrice.getText().trim();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Stock");
        alert.setHeaderText(null);

        if (code.isEmpty() || name.isEmpty() || qty.isEmpty() || ROL.isEmpty() || price.isEmpty()) {
            alert.setContentText("All fields should be filled");
        }
        else {
            try{
                System.out.println(qty);
                System.out.println(ROL);
                System.out.println(price);
                result = Stock.addStock(code, name, Integer.parseInt(qty), Integer.parseInt(ROL), new BigDecimal(price));
                System.out.println(result);
                if (result == 1) {
                    alert.setContentText("Operation Successful!");
                } else {
                    alert.setContentText("Operation Failed");
                }
            }
            catch (NumberFormatException e1) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Error");
                alert2.setHeaderText(null);
                alert2.setContentText(e1.getMessage());
                alert2.show();

            }
        }

        alert.show();
        stockCode.clear();
        stockName.clear();
        stockQty.clear();
        stockROL.clear();
        stockPrice.clear();
    }


    @FXML
    private void doAddDetail() throws SQLException, ClassNotFoundException {
        int result = 0 ;
        String code = addCode.getText().trim();
        String supId = addSup.getText().trim();
        String qty = addQty.getText().trim();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Purchase");
        alert.setHeaderText(null);
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Error");
        alert2.setHeaderText(null);

        if (code.isEmpty() || supId.isEmpty() || (addDate.getValue() == null) || qty.isEmpty()) {
            alert.setContentText("All fields should be filled");
        }
        else {
            try{
                result = Purchase.addPurchase(code, supId, addDate.getValue(), Integer.parseInt(qty));
            }
            catch (NumberFormatException e1) {
                alert2.setContentText(e1.getMessage()+"\nQuantity should be of type Integer");
                alert2.show();
            }

            if (result == 1) {
                alert.setContentText("Operation Successful!");
            } else {
                alert.setContentText("Operation Failed");
            }
        }

        alert.show();

        addCode.clear();
        addSup.clear();
        addDate.setValue(null);
        addQty.clear();
        try {
            purTable.setItems(Purchase.getPurchase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }