package inventory.sales;

import inventory.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Ranjitha on 5/3/2017.
 */
public class SalesViewController implements Initializable {

    private Main main;

    @FXML
    private TextField itemColumn;

    @FXML
    private TextField nicColumn;

    @FXML
    private TextField quantity;

    @FXML
    private DatePicker date;

    @FXML
    TableView<SalesDetails> salesTbl;
    @FXML
    TableColumn itemCodeCol;
    @FXML
    TableColumn cusNic;
    @FXML
    TableColumn quant;
    @FXML
    TableColumn coldate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itemCodeCol.setCellValueFactory(new PropertyValueFactory<SalesDetails,String>("itemCode"));
        cusNic.setCellValueFactory(new PropertyValueFactory<SalesDetails,String>("nic"));
        quant.setCellValueFactory(new PropertyValueFactory<SalesDetails,Integer>("quantity"));
        coldate.setCellValueFactory(new PropertyValueFactory<SalesDetails,Integer>("date"));



        try {
            salesTbl.setItems(Sales.getSales());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addNewSale() throws SQLException, ClassNotFoundException {
        int result;
        result = Sales.addSales(itemColumn.getText(),nicColumn.getText(),Integer.parseInt(quantity.getText()),date.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Added new sales");
        alert.setHeaderText(null);

        if (result == 1) {
            alert.setContentText("Operation Successful!");
              }

        else {
            alert.setContentText("Operation Failed");
        }

        alert.show();
        itemColumn.clear();
        nicColumn.clear();
        quantity.clear();
        date.setValue(null);

        try {
            salesTbl.setItems(Sales.getSales());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }















    }
