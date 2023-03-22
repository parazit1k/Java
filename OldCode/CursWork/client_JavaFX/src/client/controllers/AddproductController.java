package client.controllers;

import client.controllers.addToList.AddToList;
import client.controllers.addToList.AddToProduct;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;

public class AddproductController {
    public TextField NameField;
    public TextField PriceField;
    public TextField CountField;
    public TextField TypeField;
    public Button AddButton;
    public ChoiceBox<String> BrandChose;
    public Button BackButton;

    AddToProduct post = new AddToProduct();
    AddToList add = new AddToList();
    ObservableList<String> brands = add.getBrands();

    public AddproductController() throws JSONException, IOException {
    }

    @FXML
    void initialize(){
        BrandChose.setItems(brands);

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

        AddButton.setOnAction(event -> {
            try {
                double pricetosend = Double.parseDouble(PriceField.getText());
                int counttosend = Integer.parseInt(CountField.getText());
                if(NameField.getText().isEmpty()){
                    System.out.println("da");
                }else if(TypeField.getText().isEmpty()){
                    System.out.println("da");
                }else if(BrandChose.getItems().isEmpty()){
                    System.out.println("da");
                }else{
                    post.addProduct("{\"name\":\""+NameField.getText()+"\",\"price\":\""+pricetosend+"\", \"count\":\""+counttosend+"\",\"type\":\""+TypeField.getText()+"\"}", BrandChose.getSelectionModel().getSelectedItem());
                    AddButton.getScene().getWindow().hide();
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
                }
            }catch (Exception e){
                System.out.println(e);
            }
        });

    }

}
