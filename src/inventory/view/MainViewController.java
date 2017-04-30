package inventory.view;

/**
 * Created by gigar on 29-Apr-17.
 */
import inventory.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainViewController {
    private Main main;

    @FXML
    private void goHome() throws IOException {
        main.showMainItems();
    }
}
