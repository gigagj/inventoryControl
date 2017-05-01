package inventory.stock;

import inventory.stock.Stock;
import inventory.stock.StockDetail;
import javafx.fxml.FXML;


import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by gigar on 30-Apr-17.
 */
public class StockDetailViewController  implements Initializable{

    @FXML
    TableView<StockDetail> stockTbl;
    @FXML
    TableColumn itemCodeCol;
    @FXML
    TableColumn itemQtyCol;
    @FXML
    TableColumn itemROL;
    @FXML
    TableColumn itemPrice;
    @FXML
    TableColumn itemName;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itemCodeCol.setCellValueFactory(new PropertyValueFactory<StockDetail,String>("itemCode"));
        itemName.setCellValueFactory(new PropertyValueFactory<StockDetail,String>("name"));
        itemQtyCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("quantity"));
        itemROL.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("reOrderLevel"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<StockDetail,BigDecimal>("price"));


        try {
            stockTbl.setItems(Stock.getStock());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
