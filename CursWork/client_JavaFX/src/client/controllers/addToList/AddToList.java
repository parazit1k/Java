package client.controllers.addToList;

import client.controllers.Brands;
import client.controllers.Product;
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


public class AddToList {

    ObservableList<Product> items = FXCollections.observableArrayList();

    public ObservableList<Product> getItems() throws IOException, JSONException {
        URL obj = new URL("http://localhost:8080/products");
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
            items.add(new Product(
                    info.getString("name"),
                    info.getJSONObject("brand").getString("brandname"),
                    info.getString("type"),
                    info.getDouble("price"),
                    info.getInt("count"),
                    info.getLong("id")));

            i++;
        }
        return items;
    }

    ObservableList<String> brands = FXCollections.observableArrayList();

    public ObservableList<String> getBrands() throws IOException, JSONException {
        brands.add("");
        URL obj = new URL("http://localhost:8080/brand");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("[Парсинг информации о брендах] - Код: "+responseCode);

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

        System.out.println(response);

        JSONArray myResponse = new JSONArray(response.toString());
        int i = 0;
        while(i < myResponse.length()) {
            JSONObject info = myResponse.getJSONObject(i);
            brands.add(info.getString("brandname"));

            i++;
        }
        return brands;
    }
}
