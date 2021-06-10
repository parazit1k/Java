package client.controllers;

import client.controllers.UserInfo.UserToEdit;
import client.controllers.addToList.UserAdd;
import client.controllers.parsing.ParseDelete;
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

public class UsersController {
    public TableColumn<User, String> Username;
    public TableColumn<User, String> Email;
    public TableColumn<User, String> LastName;
    public TableColumn<User, String> Adress;
    public TableColumn<User, String> Name;
    public Button EditButton;
    public Button DeleteButton;
    public Button BackButton;
    public TableView<User> MainTable;

    UserAdd add = new UserAdd();
    ObservableList<User> users = add.getInfo();
    ParseDelete del = new ParseDelete();
    UserEditController userid = new UserEditController();

    public UsersController() throws JSONException, IOException {
    }

    @FXML
    void initialize(){
        Username.setCellValueFactory(new PropertyValueFactory<>("username"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        MainTable.setItems(users);

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
                    del.delete(MainTable.getSelectionModel().getSelectedItem().getId());
                    users.clear();
                    users = add.getInfo();
                    MainTable.setItems(users);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        EditButton.setOnAction(event -> {
            if(!MainTable.getSelectionModel().isEmpty()) {
                UserToEdit.setId(MainTable.getSelectionModel().getSelectedItem().getId());
                EditButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("fxml/edituser.fxml"));
                try {
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

    }
}
