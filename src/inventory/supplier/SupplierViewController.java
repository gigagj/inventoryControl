package inventory.supplier;

import inventory.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Vinush on 5/2/2017.
 */
public class SupplierViewController {

    private Main main;

    @FXML
    private TextField supCode;

    @FXML
    private TextField supName;

    @FXML
    private TextField supAdd;

    @FXML
    private TextField supCont;


    @FXML
    private TextField upSupCode;


    @FXML
    private TextField upSupAdd;

    @FXML
    private TextField upSupCont;

    @FXML
    private void goSupplierDetails() throws IOException, SQLException, ClassNotFoundException {
        main.showSupplierDetailsStage();

    }

    @FXML
    private void doDeleteSupplier() throws SQLException, ClassNotFoundException {
        int result = Supplier.deleteSupplier(supCode.getText());

        supCode.clear();

        if (result == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Supplier");
            alert.setHeaderText(null);
            alert.setContentText("Supplier Successfully Deleted!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Supplier");
            alert.setHeaderText(null);
            alert.setContentText("Deletion Failed! \n Supplier Not Found!");
            alert.show();
        }

    }

    @FXML
    private void doUpdateSupplier() throws SQLException, ClassNotFoundException, IOException {
        int result;

        if(upSupAdd.getText().trim().equals("")){
            result=Supplier.updateSupplier(upSupCode.getText(),Integer.parseInt(upSupCont.getText()));
        }
        else if (upSupCont.getText().trim().equals("")){
            result=Supplier.updateSupplier(upSupCode.getText(),upSupAdd.getText());
        }
        else{
            result=Supplier.updateSupplier(upSupCode.getText(),upSupAdd.getText(),Integer.parseInt(upSupCont.getText()));
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Supplier");
        alert.setHeaderText(null);

        if (result == 1) {
            alert.setContentText("Operation Successful!");
        } else {
            alert.setContentText("Operation Failed");
        }

        alert.show();
        upSupCont.clear();
        upSupAdd.clear();
        upSupCode.clear();

    }

    @FXML
    private void doAddSupplier() throws SQLException, ClassNotFoundException {
        int result=Supplier.addSupplier(supCode.getText(),supName.getText(),supAdd.getText(),Integer.parseInt(supCont.getText()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Supplier Added");
        alert.setHeaderText(null);

        if (result == 1) {
            alert.setContentText("Operation Successful!");
        } else {
            alert.setContentText("Operation Failed");
        }

        alert.show();
        supCode.clear();
        supName.clear();
        supAdd.clear();
        supCont.clear();

    }



}
