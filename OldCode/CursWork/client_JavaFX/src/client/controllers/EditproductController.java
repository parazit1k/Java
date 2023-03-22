package client.controllers;

import client.controllers.addToList.AddToList;
import client.controllers.addToList.AddToProduct;
import client.controllers.addToList.BrandId;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;

public class EditproductController {

    static Long id;
    static String brandname;
    static Double price;
    static String type;
    static String name;
    static Integer count;

    public TextField PriceLabel;
    public TextField TypeLabel;
    public TextField CountLabel;
    public TextField NameLabel;
    public Button ChangeButton;
    public ChoiceBox<String> BrandBox;
    public Button BackButton;
    public Label BrandCheck;

    AddToList add = new AddToList();
    ObservableList<String> brands = add.getBrands();

    public static Long getId() {
        return id;
    }

    public static Double getPrice() {
        return price;
    }

    public static void setPrice(Double price) {
        EditproductController.price = price;
    }

    public static Integer getCount() {
        return count;
    }

    public static void setCount(Integer count) {
        EditproductController.count = count;
    }

    public static void setId(Long id) {
        EditproductController.id = id;
    }

    public static String getBrandname() {
        return brandname;
    }

    public static void setBrandname(String brandname) {
        EditproductController.brandname = brandname;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        EditproductController.type = type;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        EditproductController.name = name;
    }

    public EditproductController() throws JSONException, IOException {
    }

    AddToProduct put = new AddToProduct();
    BrandId br = new BrandId();

    @FXML
    void initialize(){
        PriceLabel.setPromptText(String.valueOf(price));
        TypeLabel.setPromptText(type);
        CountLabel.setPromptText(String.valueOf(count));
        NameLabel.setPromptText(name);

        BrandBox.setItems(brands);
        BrandBox.setValue(brandname);
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
        ChangeButton.setOnAction(event -> {
            if(!BrandBox.getSelectionModel().getSelectedItem().isEmpty()){
                if(PriceLabel.getText().isEmpty()){
                    PriceLabel.setText(String.valueOf(price));
                    setPrice(Double.valueOf(PriceLabel.getText()));
                }
                if(CountLabel.getText().isEmpty()){
                    CountLabel.setText(String.valueOf(count));
                    setCount(Integer.valueOf(CountLabel.getText()));
                }
                if(NameLabel.getText().isEmpty()){
                    NameLabel.setText(name);
                    setName(NameLabel.getText());
                }
                if(TypeLabel.getText().isEmpty()){
                    TypeLabel.setText(type);
                    setType(TypeLabel.getText());
                }
                try {
                    put.editProduct("{\"id\":\""+id+"\", \"type\":\""+TypeLabel.getText()+"\", \"name\":\""+NameLabel.getText()+"\", \"price\":\""+PriceLabel.getText()+"\",\"count\":\""+CountLabel.getText()+"\", \"brand\":{\"brandname\":\""+BrandBox.getSelectionModel().getSelectedItem()+"\"}}");

                    PriceLabel.setPromptText(String.valueOf(price));
                    TypeLabel.setPromptText(type);
                    CountLabel.setPromptText(String.valueOf(count));
                    NameLabel.setPromptText(name);

                    PriceLabel.setText("");
                    CountLabel.setText("");
                    NameLabel.setText("");
                    TypeLabel.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    System.out.println(e);
                }
            }else {
                try {
                    put.editProduct("{\"id\":\""+id+"\", \"type\":\""+TypeLabel.getText()+"\", \"name\":\""+NameLabel.getText()+"\", \"price\":\""+PriceLabel.getText()+"\",\"count\":\""+CountLabel.getText()+"\", \"brand\":{\"brandname\":\""+brandname+"\"}}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
