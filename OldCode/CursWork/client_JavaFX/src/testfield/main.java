package testfield;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import client.controllers.UserInfo.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class main {
    public static void main(String[] args) throws IOException, JSONException {
        URL obj = new URL("http://localhost:8080/products");
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
            System.out.println(info.getJSONObject("brand").getString("brandname"));
            i++;
        }
    }
}
