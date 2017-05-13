package inventory.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gigar on 13-May-17.
 */
public class SearchViewController implements Initializable {

    ObservableList<String> mainTypeList = FXCollections.observableArrayList("sales","purchase","stock","supplier");
    ObservableList<String> stockSubList = FXCollections.observableArrayList("Item Code","Name");
    ObservableList<String> purchaseSubList = FXCollections.observableArrayList("Item Code","Purchase Date");
    ObservableList<String> supplierSubList = FXCollections.observableArrayList("Supplier ID","Name");
    ObservableList<String> salesSubList = FXCollections.observableArrayList("Item Code","Sales Date");

    @FXML
    private ComboBox mainTypeBox;
    @FXML
    private ComboBox subTypeBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainTypeBox.setValue("sales");
        mainTypeBox.setItems(mainTypeList);

        subTypeBox.setValue("Item Code");
        subTypeBox.setItems(salesSubList);
    }

    @FXML
    private void chooseSubList() {
        if (mainTypeBox.getValue().equals("sales")) {
            subTypeBox.setValue("Item Code");
            subTypeBox.setItems(salesSubList);
        } else if (mainTypeBox.getValue().equals("purchase")) {
            subTypeBox.setValue("Item Code");
            subTypeBox.setItems(purchaseSubList);
        } else if (mainTypeBox.getValue().equals("stock")) {
            subTypeBox.setValue("Item Code");
            subTypeBox.setItems(stockSubList);
        } else if (mainTypeBox.getValue().equals("supplier")) {
            subTypeBox.setValue("Supplier ID");
            subTypeBox.setItems(supplierSubList);
        }
    }
}
