package client.controllers;

import client.controllers.UserInfo.User;
import client.controllers.parsing.ParsePUT;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    public Label StatusBar;
    ParsePUT put = new ParsePUT();

    public Button BackProfileButton;
    public Label NickNameProfile;
    public TextField UsernameLabel;
    public TextField EmailLabel;
    public TextField AdressLabel;
    public TextField NameLabel;
    public TextField OldPasswordLabel;
    public TextField NewPasswordLabel;
    public TextField LastNameLabel;
    public Button ChangeButton;

    @FXML
    void initialize(){

        UsernameLabel.setPromptText(User.getUsername());
        EmailLabel.setPromptText(User.getEmail());
        AdressLabel.setPromptText(User.getAdress());
        NameLabel.setPromptText(User.getName());
        LastNameLabel.setPromptText(User.getLastname());

        ChangeButton.setOnAction(event -> {
            String info;
            if(OldPasswordLabel.getText().equals(User.getPassword())){
                info = "{\"id\": \"" + User.getId() + "\",\"username\": \"" + UsernameLabel.getText() + "\", \"password\": \"" + NewPasswordLabel.getText() + "\", \"name\": \"" + NameLabel.getText() + "\", \"lastname\": \"" + LastNameLabel.getText() + "\", \"email\": \"" + EmailLabel.getText() + "\", \"adress\": \"" + AdressLabel.getText() + "\"}";
            }else{
                info = "{\"id\": \"" + User.getId() + "\",\"username\": \"" + UsernameLabel.getText() + "\", \"name\": \"" + NameLabel.getText() + "\", \"lastname\": \"" + LastNameLabel.getText() + "\", \"email\": \"" + EmailLabel.getText() + "\", \"adress\": \"" + AdressLabel.getText() + "\"}";
            }

            try {
                StatusBar.setText(put.PUT("http://localhost:8080/users", info));
                if(!UsernameLabel.getText().isEmpty()){
                    User.setUsername(UsernameLabel.getText());
                }
                if(!EmailLabel.getText().isEmpty()){
                    User.setEmail(EmailLabel.getText());
                }
                if(!AdressLabel.getText().isEmpty()){
                    User.setAdress(AdressLabel.getText());
                }
                if(!NameLabel.getText().isEmpty()){
                    User.setName(NameLabel.getText());
                }
                if(!LastNameLabel.getText().isEmpty()){
                    User.setLastname(LastNameLabel.getText());
                }
                if(OldPasswordLabel.getText().equals(User.getPassword())){
                    User.setPassword(NewPasswordLabel.getText());
                }

                UsernameLabel.setText("");
                EmailLabel.setText("");
                AdressLabel.setText("");
                NameLabel.setText("");
                LastNameLabel.setText("");
                UsernameLabel.setPromptText(User.getUsername());
                EmailLabel.setPromptText(User.getEmail());
                AdressLabel.setPromptText(User.getAdress());
                NameLabel.setPromptText(User.getName());
                LastNameLabel.setPromptText(User.getLastname());
                NickNameProfile.setText(User.getUsername()+"'s Profile");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        BackProfileButton.setOnAction(event -> {
            BackProfileButton.getScene().getWindow().hide();
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
        NickNameProfile.setText(User.getUsername()+"'s Profile");
    }
}
