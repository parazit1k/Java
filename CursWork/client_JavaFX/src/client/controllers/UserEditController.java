package client.controllers;

import client.controllers.UserInfo.User;
import client.controllers.UserInfo.UserToEdit;
import client.controllers.parsing.ParsePUT;
import client.controllers.parsing.UserGet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class UserEditController {
    public TextField UsernameLabel;
    public TextField EmailLabel;
    public TextField AdressLabel;
    public TextField NameLabel;
    public Button ChangeButton;
    public TextField LastNameLabel;
    public Label StatusBar;
    public Button BackProfileButton;
    public Label NickNameProfile;

    public Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    UserGet get = new UserGet();
    ParsePUT put = new ParsePUT();

    @FXML
    void initialize() throws JSONException, IOException {
        get.user(UserToEdit.getId());
        UsernameLabel.setPromptText(UserToEdit.getUsername());
        EmailLabel.setPromptText(UserToEdit.getEmail());
        AdressLabel.setPromptText(UserToEdit.getAdress());
        NameLabel.setPromptText(UserToEdit.getName());
        LastNameLabel.setPromptText(UserToEdit.getLastname());

        BackProfileButton.setOnAction(event -> {
            BackProfileButton.getScene().getWindow().hide();
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
        });

        ChangeButton.setOnAction(event -> {
            try {
                String text = "{\"id\": \"" + UserToEdit.getId() + "\",\"username\": \"" + UsernameLabel.getText() + "\", \"name\": \"" + NameLabel.getText() + "\", \"lastname\": \"" + LastNameLabel.getText() + "\", \"email\": \"" + EmailLabel.getText() + "\", \"adress\": \"" + AdressLabel.getText() + "\"}";
                put.PUT("http://localhost:8080/users", text);
                ChangeButton.getScene().getWindow().hide();
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
