package client.controllers;

import client.controllers.UserInfo.User;
import client.controllers.addToList.AddBin;
import client.controllers.checkConnection.check;
import client.controllers.parsing.ParseGET;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    public Circle conCheck;
    ParseGET parse = new ParseGET();
    check check = new check();
    AddBin bin = new AddBin();

    @FXML
    private Button CloseButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField UsernameField;

    @FXML
    private javafx.scene.control.PasswordField PasswordField;

    @FXML
    private Button AuthorizationButton;

    @FXML
    private Button RegistrationButton;

    @FXML
    private Label ErrorLabelLogin;

    @FXML
    private Button checkButton;

    @FXML
    void initialize() {

        CloseButton.setVisible(false);

        AuthorizationButton.setOnAction(event -> {
            if (UsernameField.getText().isEmpty()) {
                if(PasswordField.getText().isEmpty()){
                    ErrorLabelLogin.setText("Не введён Логин и Пароль!");
                }else{
                    PasswordField.setText("");
                    ErrorLabelLogin.setText("Не введён Логин!");
                }
            } else if (PasswordField.getText().isEmpty()){
                UsernameField.setText("");
                ErrorLabelLogin.setText("Не введён Пароль!");
            }else{
                ErrorLabelLogin.setText("");
                if(check.checkCon()) {
                    conCheck.setFill(Paint.valueOf("#00ff00"));
                    try {
                        if (parse.GET(UsernameField.getText().toLowerCase(), PasswordField.getText())) {
                            AuthorizationButton.getScene().getWindow().hide();
                            bin.addtobin("{}", User.getId());
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
                        } else {
                            if (check.checkCon()) {
                                conCheck.setFill(Paint.valueOf("#00ff00"));
                                ErrorLabelLogin.setText("Не правильный Логин или Пароль!");
                            } else {
                                conCheck.setFill(Paint.valueOf("#ff0000"));
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    System.out.println(UsernameField.getText() + " " + PasswordField.getText());
                    ErrorLabelLogin.setText("Нет подключения!");
                    conCheck.setFill(Paint.valueOf("#ff0000"));
                    UsernameField.setText("");
                    PasswordField.setText("");
                }
            }
        });
        RegistrationButton.setOnAction(event ->{
            RegistrationButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/registration.fxml"));
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
        CloseButton.setOnAction(event -> {
            CloseButton.getScene().getWindow().hide();
        });
        checkButton.setOnAction(event -> {
            if(check.checkCon()) {
                conCheck.setFill(Paint.valueOf("#00ff00"));
            }else {
                conCheck.setFill(Paint.valueOf("#ff0000"));
            }
        });
        if(check.checkCon()) {
            conCheck.setFill(Paint.valueOf("#00ff00"));
        }else {
            conCheck.setFill(Paint.valueOf("#ff0000"));
        }
    }
}
