package client.controllers.addToList;

import client.controllers.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AddToBin {

    ObservableList<Product> items = FXCollections.observableArrayList();

    public ObservableList<Product> getItems(Long id) throws IOException, JSONException {
        try{
        URL obj = new URL("http://localhost:8080/bin?UserId="+ String.valueOf(id));
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

        System.out.println(response);

        JSONObject myResponse = new JSONObject(response.toString());
        int i = 0;
        JSONArray check = new JSONArray(myResponse.getJSONArray("products").toString());
        System.out.println(check);
        int j = 0;
        while(j < check.length()){
            JSONObject productinfo = check.getJSONObject(j);
            System.out.println(productinfo);
            items.add(new Product(
                    productinfo.getString("name"),
                    productinfo.getJSONObject("brand").getString("brandname"),
                    productinfo.getString("type"),
                    productinfo.getDouble("price"),
                    productinfo.getInt("count"),
                    productinfo.getLong("id")
            ));
            j++;
        }
        return items;
        }catch (Exception e){
            System.out.println(e);
            return items;
        }
    }

    public void addToBin(Long userId, Long productId) throws IOException {
        URL url = new URL("http://localhost:8080/bin?userId="+userId+"&productid="+productId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("PUT");

        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);

        in.close();
        connection.disconnect();
    }

    public void deleteFromBin(Long userId, Long productId) throws IOException {
        URL url = new URL("http://localhost:8080/bin?userId="+userId+"&productid="+productId+"&type=1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("DELETE");

        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);

        in.close();
        connection.disconnect();
    }
}
