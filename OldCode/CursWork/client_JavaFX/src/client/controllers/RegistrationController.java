package client.controllers;

import client.controllers.parsing.ParsePOST;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {

    public Label StatusLabel;
    ParsePOST post = new ParsePOST();

    public javafx.scene.control.TextField FieldRegistrationName;
    public javafx.scene.control.TextField FieldRegistrationLastname;
    public javafx.scene.control.TextField FieldRegistrationPassword;
    public javafx.scene.control.TextField FieldRegistrationEmail;
    public javafx.scene.control.TextField FieldRegistrationAdress;
    public TextField FieldRegistrationUsername;

    @FXML
    private Button RegistrationButton;

    @FXML
    private CheckBox CheckBoxMan;

    @FXML
    private CheckBox CheckBoxFemale;

    @FXML
    private Button BackButtonRegistration;

    @FXML
    void initialize() {
        BackButtonRegistration.setOnAction(event ->{
            BackButtonRegistration.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/login.fxml"));
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
        RegistrationButton.setOnAction(event -> {
            try {
                String sex;
                if(CheckBoxFemale.isSelected()){
                    sex = CheckBoxFemale.getText();
                }else if(CheckBoxMan.isSelected()){
                    sex = CheckBoxMan.getText();
                }else{
                    sex = "none";
                }
                String text = "{\"username\": \"" + FieldRegistrationUsername.getText() + "\", \"password\": \"" + FieldRegistrationPassword.getText() + "\", \"name\": \"" + FieldRegistrationName.getText() + "\", \"lastname\": \"" + FieldRegistrationLastname.getText() + "\", \"email\": \"" + FieldRegistrationEmail.getText() + "\", \"adress\": \"" + FieldRegistrationAdress.getText() + "\", \"sex\": \"" + sex + "\", \"type\": \"client\"}";
                StatusLabel.setText(post.POST(text,"http://localhost:8080/users"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleManBox(){
        if(CheckBoxMan.isSelected()){
            CheckBoxFemale.setSelected(false);
        }
    }

    @FXML
    private void handleFemaleBox(){
        if(CheckBoxFemale.isSelected()){
            CheckBoxMan.setSelected(false);
        }
    }

}
