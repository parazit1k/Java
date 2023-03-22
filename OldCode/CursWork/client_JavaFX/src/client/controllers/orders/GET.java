package client.controllers.orders;

import client.controllers.Orders;
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

public class GET {

    ObservableList<Orders> items = FXCollections.observableArrayList();

    public ObservableList<Orders> fillTable(Long userid) throws IOException, JSONException {
        try {
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
                System.out.println(info);
                System.out.println(info.getJSONObject("userEntity"));
                System.out.println(userid);
                if (info.getJSONObject("userEntity").getLong("id") == userid) {
                    System.out.println("da");
                    List<String> ta = new ArrayList<>();
                    JSONArray pr = info.getJSONArray("productsOrder");
                    j = 0;
                    while (j < pr.length()) {
                        JSONObject object = pr.getJSONObject(j);
                        System.out.println(object.getString("name"));
                        ta.add(object.getString("name"));
                        j++;
                    }
                    items.add(new Orders(
                            info.getLong("id"),
                            ta
                    ));
                }
                i++;
            }
            for(Orders g: items){
                System.out.println(g.getOrderid()+" "+ g.getProducts());
            }
            return items;
        } catch (Exception e) {
            System.out.println(e);
            return items;
        }
    }
}
