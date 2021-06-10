package client.controllers.addToList;

import client.controllers.Product;
import client.controllers.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserAdd {

    ObservableList<User> toSend = FXCollections.observableArrayList();

    public ObservableList<User> getInfo() throws IOException, JSONException {
        URL obj = new URL("http://localhost:8080/users");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("[Парсинг информации о товарах] - Код: "+responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();

        JSONArray myResponse = new JSONArray(response.toString());
        int i = 0;
        while(i < myResponse.length()) {
            JSONObject info = myResponse.getJSONObject(i);
            toSend.add(new User(
                    info.getString("username"),
                    info.getString("name"),
                    info.getString("lastname"),
                    info.getString("email"),
                    info.getString("adress"),
                    info.getLong("id")
            ));

            i++;
        }
        return toSend;
    }

}
