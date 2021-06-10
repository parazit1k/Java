package client.controllers.addToList;

import client.controllers.UserInfo.User;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BrandId {

    public Long getBrandId(String brandname) throws IOException, JSONException {
        URL obj = new URL("http://localhost:8080/brand");
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
        System.out.println("[Поиск Id бренда] - Код: " + responseCode);

        System.out.println(response);
        JSONArray myResponse = new JSONArray(response.toString());
        int i = 0;
        while (i < myResponse.length()) {
            JSONObject info = myResponse.getJSONObject(i);
            if(info.getString("brandname").equals(brandname)){
                return info.getLong("id");
            }
            i++;
        }
        return 0L;
    }

}
