package client.controllers.orders;

import client.controllers.UsersOrders;
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
import java.util.ArrayList;
import java.util.List;

public class AddToOrders {

    ObservableList<UsersOrders> toSend = FXCollections.observableArrayList();

    public ObservableList<UsersOrders> getOrders() throws IOException, JSONException {
        URL obj = new URL("http://localhost:8080/orders");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            response.append(inputLine);
        }
        in.close();
        System.out.println("[Парсинг информации о пользователях] - Код: " + responseCode);

        JSONArray myResponse = new JSONArray(response.toString());
        System.out.println(myResponse);
        int i = 0;
        int j;
        while (i < myResponse.length()) {
            JSONObject info = myResponse.getJSONObject(i);
            List<String> product = new ArrayList<>();
            j = 0;
            JSONArray ar = info.getJSONArray("productsOrder");
            while(j < ar.length()) {
                JSONObject object = ar.getJSONObject(j);
                product.add(object.getString("name"));
                j++;
            }
            System.out.println(product);
            toSend.add(new UsersOrders(
                    info.getJSONObject("userEntity").getString("username"),
                    info.getLong("id"),
                    product
            ));
            i++;
            System.out.println(toSend.get(0).getProducts());
        }
        return toSend;
        }
    }
