package client.controllers;

import client.Main;
import client.controllers.addToList.AddToProduct;
import client.controllers.orders.DELETE;
import client.controllers.orders.DELETEorder;
import client.controllers.orders.GET;
import javafx.collections.FXCollections;
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

public class OrderController {
    public Button DeleteOrder;
    public TableView<Orders> MainTable;
    public TableColumn<Orders, Long> IdColumn;
    public TableColumn<Orders, List<String>> ProductColumn;
    public Button BackButton;

    GET get = new GET();
    ObservableList<Orders> orders = get.fillTable(client.controllers.UserInfo.User.getId());
    AddToProduct put = new AddToProduct();


    DELETE delete = new DELETE();
    DELETEorder delord = new DELETEorder();

    public OrderController() throws JSONException, IOException {
    }

    @FXML
    void initialize() throws JSONException, IOException {

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        ProductColumn.setCellValueFactory(new PropertyValueFactory<>("products"));
        System.out.println(client.controllers.UserInfo.User.getId());
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

        DeleteOrder.setOnAction(event -> {
            if(!MainTable.getSelectionModel().isEmpty()){
                try {
                    Long orderId = MainTable.getSelectionModel().getSelectedItem().getOrderid();
                    delord.delete(orderId);

                    delete.deleteOrder(MainTable.getSelectionModel().getSelectedItem().getOrderid());
                    orders.clear();
                    orders = get.fillTable(client.controllers.UserInfo.User.getId());
                    MainTable.setItems(orders);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
