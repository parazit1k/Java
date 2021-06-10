package client.controllers;

import client.controllers.UserInfo.User;
import client.controllers.addToList.AddToBin;
import client.controllers.addToList.AddToList;
import client.controllers.addToList.AddToProduct;
import client.controllers.brand.GET;
import client.controllers.brand.GETID;
import client.controllers.brand.POST;
import client.controllers.brand.PUT;
import client.controllers.find.find;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;

public class ShopController {

    public ChoiceBox<String> BrandBox;
    public Button UsersButton;
    public Label StatusLabel;
    public Button OrdersButton;
    public TextField AddBrandLabel;
    public Button AddBrandButton;
    public TextField StartNameLabel;
    public TextField FinalBrandLabel;
    public Button ChangeBrandButton;
    public Button RefreshButton;
    public Button UsersOderButton;
    AddToProduct pr = new AddToProduct();

    public TextField NameFind;
    public Button ChangeButton;
    public Button AddButton;
    public Button DeleteButton;
    AddToBin bin = new AddToBin();

    public TableColumn<Product, Long> IdColumn;
    public TableColumn<Product, Integer> CountColumn;
    public Button ShopFindButton;
    public TableView<Product> MainTable;
    public TableColumn<Product, String> TypeColumn;
    public TableColumn<Product, String> CompanyColumn;
    public TableColumn<Product, String> NameColumn;
    public TableColumn<Product, Double> PriceColumn;
    public Button ShopProfileButton;
    public Button InfoButtonShop;
    public Button BinButtonShop;
    public Button ToBinButton;

    AddToList add = new AddToList();
    find find = new find();
    GET get = new GET();
    POST post = new POST();
    GETID getid = new GETID();
    PUT put = new PUT();
    ObservableList<Product> products = add.getItems();
    ObservableList<String> brands = add.getBrands();


    public ShopController() throws JSONException, IOException {
    }

    @FXML
    void initialize(){
        System.out.println(User.getId());

        
        if(User.getType().equals("client")){
            ChangeButton.setVisible(false);
            DeleteButton.setVisible(false);
            AddButton.setVisible(false);
            UsersButton.setVisible(false);
            AddBrandLabel.setVisible(false);
            AddBrandButton.setVisible(false);
            StartNameLabel.setVisible(false);
            StartNameLabel.setVisible(false);
            FinalBrandLabel.setVisible(false);
            ChangeBrandButton.setVisible(false);
            UsersOderButton.setVisible(false);

        }

        BrandBox.setItems(brands);

        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        CompanyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        CountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        MainTable.setItems(products);

        RefreshButton.setOnAction(event -> {
            brands.clear();
            products.clear();
            try {
                products = add.getItems();
                brands = add.getBrands();
                BrandBox.setItems(brands);
                MainTable.setItems(products);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });

        UsersOderButton.setOnAction(event -> {
            UsersOderButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/usersorders.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Parazitik's Shop");
            stage.setResizable(false);
            stage.show();
        });

        AddBrandButton.setOnAction(event -> {
            if(User.getType().equals("admin")) {
                try {
                    if (!AddBrandLabel.getText().isEmpty() && get.getBrands(AddBrandLabel.getText())) {
                        System.out.println("da");
                        post.addBrand("{\"brandname\":\"" + AddBrandLabel.getText() + "\"}");
                        AddBrandLabel.setText("");
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ChangeBrandButton.setOnAction(event -> {
            if(User.getType().equals("admin")) {
                try {
                    if (!get.getBrands(StartNameLabel.getText()) && !FinalBrandLabel.getText().isEmpty()) {
                        System.out.println("da");
                        put.changeBrand(getid.getBrandId(StartNameLabel.getText()), FinalBrandLabel.getText());
                        StartNameLabel.setText("");
                        FinalBrandLabel.setText("");
                    } else {
                        System.out.println("net");
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ShopFindButton.setOnAction(event -> {
            StatusLabel.setText("");
            if(BrandBox.getSelectionModel().isEmpty() || BrandBox.getSelectionModel().getSelectedItem().equals("")){
                if(!TypeColumn.getText().isEmpty()){
                    try {
                        products.clear();
                        products = find.findbytype(NameFind.getText());
                        MainTable.setItems(products);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                if(!TypeColumn.getText().isEmpty()){
                    try {
                        products.clear();
                        products = find.findbytypeandbrand(NameFind.getText(), BrandBox.getSelectionModel().getSelectedItem());
                        MainTable.setItems(products);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        products.clear();
                        products = find.findbybrand(BrandBox.getSelectionModel().getSelectedItem());
                        MainTable.setItems(products);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        OrdersButton.setOnAction(event -> {
            OrdersButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/order.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Parazitik's Shop");
            stage.setResizable(false);
            stage.show();
        });

        UsersButton.setOnAction(event -> {
            if(User.getType().equals("admin")){
                UsersButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("fxml/users.fxml"));
                try{
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 600, 400));
                stage.setTitle("Parazitik's Shop");
                stage.setResizable(false);
                stage.show();
            }
        });

        DeleteButton.setOnAction(event -> {
                    if (User.getType().equals("admin")) {
                        if (!MainTable.getSelectionModel().isEmpty()) {
                            try {
                                pr.deleteProduct(MainTable.getSelectionModel().getSelectedItem().getId());
                                products.clear();
                                products = add.getItems();
                                TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                                CompanyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
                                NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                                PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                                CountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
                                IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                                MainTable.setItems(products);
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        AddButton.setOnAction(event -> {
            AddButton.getScene().getWindow().hide();
            if(User.getType().equals("admin")){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("fxml/addproduct.fxml"));
                try{
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 600, 400));
                stage.setTitle("Parazitik's Shop");
                stage.setResizable(false);
                stage.show();
            }
        });



        ChangeButton.setOnAction(event -> {
            if(User.getType().equals("admin")){
                if(!MainTable.getSelectionModel().isEmpty()){
                    ChangeButton.getScene().getWindow().hide();
                    EditproductController.setBrandname(MainTable.getSelectionModel().getSelectedItem().getCompany());
                    EditproductController.setId(MainTable.getSelectionModel().getSelectedItem().getId());
                    EditproductController.setName(MainTable.getSelectionModel().getSelectedItem().getName());
                    EditproductController.setPrice(MainTable.getSelectionModel().getSelectedItem().getPrice());
                    EditproductController.setType(MainTable.getSelectionModel().getSelectedItem().getType());
                    EditproductController.setCount(MainTable.getSelectionModel().getSelectedItem().getCount());

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("fxml/editproduct.fxml"));
                    try{
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 600, 400));
                    stage.setTitle("Parazitik's Shop");
                    stage.setResizable(false);
                    stage.show();
                }
            }
        });

        InfoButtonShop.setOnAction(event -> {
            InfoButtonShop.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/info.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Parazitik's Shop");
            stage.setResizable(false);
            stage.show();
        });

        ToBinButton.setOnAction(event -> {
            StatusLabel.setText("");
            if(!MainTable.getSelectionModel().isEmpty()){
                try {
                    bin.addToBin(User.getId(), MainTable.getSelectionModel().getSelectedItem().getId());
                    StatusLabel.setText("Успешно добавлено в корзину");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        BinButtonShop.setOnAction(event -> {
            BinButtonShop.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/bin.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 700, 450));
            stage.setTitle("Parazitik's Shop");
            stage.setResizable(false);
            stage.show();
        });

        ShopProfileButton.setOnAction(event -> {
            ShopProfileButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/profile.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 700, 350));
            stage.setTitle("Parazitik's Shop");
            stage.setResizable(false);
            stage.show();
        });

    }
}
