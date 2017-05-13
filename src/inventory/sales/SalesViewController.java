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
        coldate.setCellValueFactory(new PropertyValueFactory<SalesDetails,String>("date"));



        try {
            salesTbl.setItems(Sales.getSales());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addNewSale() throws SQLException, ClassNotFoundException {
        int result =0;
        String checkItemColumn= itemColumn.getText();
        String checkNicColumn= nicColumn.getText();
        String checkQuantity =quantity.getText();



        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Added new sales");
        alert.setHeaderText(null);

        if (checkItemColumn.isEmpty() && checkNicColumn.isEmpty() && checkQuantity.isEmpty() && date.getValue() == null ) {
            alert.setContentText("All Fields cannot be empty");
        }
        else if (checkItemColumn.isEmpty()|| checkNicColumn.isEmpty() || checkQuantity.isEmpty() || date.getValue() == null )  {
            alert.setContentText("Any of the fields cannot be empty");

        }



        else {
            try {
                result = Sales.addSales(itemColumn.getText(), nicColumn.getText(), Integer.parseInt(quantity.getText()), date.getValue());
            }
            catch(Exception e){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

                alert2.setHeaderText("error");

                alert2.setContentText("number should be entered for quantity");
                alert2.show();
            }
            if (result == 1) {
                alert.setContentText("Operation Succession");
            } else {
                alert.setContentText("Operation Failed");
            }

            try {
                salesTbl.setItems(Sales.getSales());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        alert.show();
        itemColumn.clear();
        nicColumn.clear();
        quantity.clear();
        date.setValue(null);


    }

    }
