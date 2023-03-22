package client.controllers;

import client.Main;
import client.controllers.orders.AddToOrders;
import client.controllers.orders.DELETE;
import client.controllers.orders.DELETEorder;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class UsersOrdersController {

    public TableView<UsersOrders> MainTable;
    public TableColumn<UsersOrders, String> UsernameColumn;
    public TableColumn<UsersOrders, Long> OrderNumberColumn;
    public TableColumn<UsersOrders, List<String>> ProductsColumn;
    public Button DeleteButton;
    public Button BackButton;

    AddToOrders add = new AddToOrders();
    ObservableList<UsersOrders> orders = add.getOrders();
    DELETEorder delord = new DELETEorder();
    DELETE delete = new DELETE();

    public UsersOrdersController() throws JSONException, IOException {
    }

    @FXML
    void initialize(){
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        OrderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        ProductsColumn.setCellValueFactory(new PropertyValueFactory<>("products"));

        MainTable.setItems(orders);

        BackButton.setOnAction(event -> {
            BackButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/shop.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 750, 520));
            stage.setTitle("Parazitik's Shop");
            stage.setResizable(false);
            stage.show();
        });

        DeleteButton.setOnAction(event -> {
            if(!MainTable.getSelectionModel().isEmpty()){
                try {
                    Long orderId = MainTable.getSelectionModel().getSelectedItem().getOrderid();
                    delord.delete(orderId);

                    delete.deleteOrder(MainTable.getSelectionModel().getSelectedItem().getOrderid());
                    orders.clear();
                    orders = add.getOrders();
                    MainTable.setItems(orders);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
