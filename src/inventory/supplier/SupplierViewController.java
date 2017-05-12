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
    private TextField addSupCode;

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
        String s = supCode.getText();
        s = s.trim();

        if (s.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Supplier");
            alert.setHeaderText(null);
            alert.setContentText("Supplier Code Field Cannot be Empty!");
            alert.show();
        }
        else {
            int result =0;
                    result= Supplier.deleteSupplier(supCode.getText());
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

    }

    @FXML
    private void doUpdateSupplier() throws SQLException, ClassNotFoundException, IOException {
        int result;

        String code = upSupCode.getText().trim();
        String address = upSupAdd.getText().trim();
        String cont = upSupCont.getText().trim();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Return Supplier");
        alert.setHeaderText(null);

        if (code.isEmpty() && address.isEmpty() && cont.isEmpty()) {
            alert.setContentText("All Fields cannot be empty");
        } else if (address.isEmpty() && cont.isEmpty() && !code.isEmpty()) {
            alert.setContentText("You have to enter at least one field to update details!");
        } else if (code.isEmpty()) {
            alert.setContentText("Supplier Code cannot be empty!");
        } else {

            if (upSupAdd.getText().trim().equals("")) {
                result = Supplier.updateSupplier(upSupCode.getText(), Integer.parseInt(upSupCont.getText()));
            } else if (upSupCont.getText().trim().equals("")) {
                result = Supplier.updateSupplier(upSupCode.getText(), upSupAdd.getText());
            } else {
                result = Supplier.updateSupplier(upSupCode.getText(), upSupAdd.getText(), Integer.parseInt(upSupCont.getText()));
            }

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Update Supplier");
            alert2.setHeaderText(null);

            if (result == 1) {
                alert.setContentText("Operation Successful!");
            } else {
                alert.setContentText("Operation Failed");
            }
        }


        alert.show();
        upSupCont.clear();
        upSupAdd.clear();
        upSupCode.clear();

    }

    @FXML
    private void doAddSupplier() throws SQLException, ClassNotFoundException {
       int result=0;
        String code = upSupAdd.getText().trim();
        String address = supAdd.getText().trim();
        String cont = supCont.getText().trim();
        String name = supName.getText().trim();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Supplier Added");
        alert.setHeaderText(null);

            if (code.isEmpty() && address.isEmpty() && cont.isEmpty() && name.isEmpty()) {
            alert.setContentText("All Fields cannot be empty"); }
            
            else {
                result = Supplier.addSupplier(addSupCode.getText(), supName.getText(), supAdd.getText(), Integer.parseInt(supCont.getText()));

                if (result == 1) {
                    alert.setContentText("Operation Successful!");
                } else {
                    alert.setContentText("Operation Failed");
                }
            }
        



        alert.show();
        addSupCode.clear();
        supName.clear();
        supAdd.clear();
        supCont.clear();

    }



}
