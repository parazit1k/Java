package client.controllers.orders;

import client.controllers.addToList.AddToProduct;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DELETEorder {

    AddToProduct put = new AddToProduct();

    public void delete(Long orderid) throws IOException, JSONException {
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
            if(info.getLong("id") == orderid){
                j = 0;
                JSONArray products = info.getJSONArray("productsOrder");
                while (j < products.length()){
                    JSONObject product = products.getJSONObject(j);
                    put.orderProduct("{\"id\":\""+product.getLong("id")+"\", \"type\":\""+product.getString("type")+"\", \"name\":\""+product.getString("name")+"\", \"price\":\""+product.getDouble("price")+"\",\"count\":\""+(product.getInt("count")+1)+"\", \"brand\":{\"brandname\":\""+product.getJSONObject("brand").getString("brandname")+"\"}}");
                    j++;
                }
            }
            i++;
        }
    }
}

