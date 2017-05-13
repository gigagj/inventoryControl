package inventory.view;

import inventory.DbConnection;
import inventory.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by Owner on 2017-05-13.
 */
public class LoginController  {
    private static  Main main;

    @FXML
    private  TextField userName;
    @FXML
    private  PasswordField password;

    @FXML
    private  void loginAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String usernameDb = null;
        String passwordDb = null;


        if (userName.getText().equals("") || password.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Enter username and password");
            alert.show();
        }
        else{
            DbConnection.openConnection();
            Connection con = DbConnection.getConnection();

            PreparedStatement stmt = con.prepareStatement("select * from admin where username = ?");
            stmt.setString(1,userName.getText());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                 usernameDb = rs.getString(1);
                 passwordDb = rs.getString(2);
            }

            if (userName.getText().equals(usernameDb) &&  password.getText().equals(passwordDb)) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                main.showMainView();
                main.showMainItems();


            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("LOGIN");
                alert.setHeaderText(null);
                alert.setContentText("Unable to login ! \n  Invalid credentials");
                alert.show();
            }
        }
    }


}
