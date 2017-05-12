package inventory.supplier;

import inventory.Main;
import inventory.stock.Stock;
import inventory.stock.StockDetail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Vinush on 5/2/2017.
 */
public class SupplierDetailViewController implements Initializable {

    @FXML
    TableView<SupplierDetail> supplierTab;
    @FXML
    TableColumn supID;
    @FXML
    TableColumn supName;
    @FXML
    TableColumn supAdd;
    @FXML
    TableColumn supCont;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        supID.setCellValueFactory(new PropertyValueFactory<SupplierDetail,String>("supplierID"));
        supName.setCellValueFactory(new PropertyValueFactory<SupplierDetail,String>("name"));
        supAdd.setCellValueFactory(new PropertyValueFactory<SupplierDetail,String>("address"));
        supCont.setCellValueFactory(new PropertyValueFactory<SupplierDetail,Integer>("contactNumber"));



        try {
            supplierTab.setItems(Supplier.getSupplier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
