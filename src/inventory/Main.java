package inventory;
/**
 * Created by gigar on 28-Apr-17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    private static Stage stage;
    private static AnchorPane layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory Control System");

        FXMLLoader root = new FXMLLoader();
        root.setLocation(Main.class.getResource("view/Login.fxml"));
        layout = root.load();
        Scene scene = new Scene(layout);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public static void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showMainItems() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainItems.fxml"));
        Pane mainItem = loader.load();
        mainLayout.setLeft(mainItem);

        FXMLLoader searchViewLoader = new FXMLLoader();
        searchViewLoader.setLocation(Main.class.getResource("view/SearchView.fxml"));
        Pane searchItem = searchViewLoader.load();
        mainLayout.setCenter(searchItem);
    }

    public static void showStock() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("stock/StockView.fxml"));
        GridPane stockPane = loader.load();
        mainLayout.setCenter(stockPane);
    }

    public static void showStockDetailStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("stock/StockDetailView.fxml"));
        BorderPane stockDetail = loader.load();

        Stage stockDetailStage = new Stage();
        stockDetailStage.setTitle("Stock Details");
        stockDetailStage.initModality(Modality.WINDOW_MODAL);
        stockDetailStage.initOwner(primaryStage);

        Scene scene = new Scene(stockDetail);
        stockDetailStage.setScene(scene);
        stockDetailStage.showAndWait();
    }

    public static void showSupplierDetailsStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("supplier/SupplierDetailView.fxml"));
        BorderPane supplierDetail = loader.load();

        Stage supplierDetailStage = new Stage();
        supplierDetailStage.setTitle("Supplier Details");
        supplierDetailStage.initModality(Modality.WINDOW_MODAL);
        supplierDetailStage.initOwner(primaryStage);

        Scene scene = new Scene(supplierDetail);
        supplierDetailStage.setScene(scene);
        supplierDetailStage.showAndWait();
    }

    public static void showPurchase() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("purchase/PurchaseView.fxml"));
        BorderPane purchasePane = loader.load();
        mainLayout.setCenter(purchasePane);
    }

    public static void logout() {
        primaryStage.hide();
        stage.show();
    }


    public static void showSupplier() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("supplier/SupplierView.fxml"));
        GridPane supplierPane = loader.load();
        mainLayout.setCenter(supplierPane);
    }

    public static void showSales() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sales/SalesView.fxml"));
        BorderPane supplierPane = loader.load();
        mainLayout.setCenter(supplierPane);
    }
}







