package client.controllers;

import client.controllers.UserInfo.User;
import client.controllers.addToList.AddToBin;
import client.controllers.addToList.AddToProduct;
import client.controllers.orders.GET;
import client.controllers.orders.POST;
import client.controllers.orders.PUT;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;

public class BinController {

    public Label StatusLabel;
    AddToBin bin = new AddToBin();

    public Button OrderBin;
    public Button BackButtonBin;
    public TableView<Product> MainTable;
    public TableColumn<Product, String> TypeColumn;
    public TableColumn<Product, String> BrandTable;
    public TableColumn<Product, String> NameTable;
    public TableColumn<Product, Long> PriceColumn;
    public TableColumn<Product, Integer> CountColumn;
    public Label NickNameLabel;
    public Button DeleteFromBin;

    AddToBin add = new AddToBin();
    AddToProduct pr = new AddToProduct();
    GET get = new GET();
    POST post = new POST();
    PUT put = new PUT();
    Long orderid;
    ObservableList<Product> products = add.getItems(User.getId());

    public BinController() throws JSONException, IOException {
    }

    @FXML
    void initialize(){
        NickNameLabel.setText(User.getUsername()+"'s Bin");
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        BrandTable.setCellValueFactory(new PropertyValueFactory<>("company"));
        NameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        MainTable.setItems(products);

        OrderBin.setOnAction(event -> {
            try {
                orderid = post.insertInfo(User.getId());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            for(Product i : products){
                try {
                    put.insertProducts(orderid, i.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    pr.orderProduct("{\"id\":\""+i.getId()+"\", \"type\":\""+i.getType()+"\", \"name\":\""+i.getName()+"\", \"price\":\""+i.getPrice()+"\",\"count\":\""+(i.getCount()-1)+"\", \"brand\":{\"brandname\":\""+i.getCompany()+"\"}}");
                    bin.deleteFromBin(User.getId(), i.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            products.clear();
            try {
                products = add.getItems(User.getId());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            MainTable.setItems(products);
            StatusLabel.setText("Заказ по адресу: "+User.getAdress()+" сделан!");
        });

        DeleteFromBin.setOnAction(event -> {
            if(!MainTable.getSelectionModel().isEmpty()){
                try {
                    bin.deleteFromBin(User.getId(), MainTable.getSelectionModel().getSelectedItem().getId());
                    products.clear();
                    products = add.getItems(User.getId());
                    TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                    BrandTable.setCellValueFactory(new PropertyValueFactory<>("company"));
                    NameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
                    PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

                    MainTable.setItems(products);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        BackButtonBin.setOnAction(event -> {
            BackButtonBin.getScene().getWindow().hide();
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
    }
}
