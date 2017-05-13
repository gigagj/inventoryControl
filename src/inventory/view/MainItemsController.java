package inventory.view;

import inventory.Main;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by gigar on 29-Apr-17.
 */
public class MainItemsController {

    private Main main;

    @FXML
    private void openStock() throws IOException {
        main.showStock();

    }
    @FXML
    private void openSupplier() throws IOException {
        main.showSupplier();

    }
}
