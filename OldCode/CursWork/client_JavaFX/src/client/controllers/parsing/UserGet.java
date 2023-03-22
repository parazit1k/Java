package client.controllers.parsing;

import client.controllers.UserInfo.UserToEdit;
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

public class UserGet {
    public boolean user(Long userid) throws IOException, JSONException {
        try {
            List<String> toSend = new ArrayList<>();
            URL obj = new URL("http://localhost:8080/users");
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
            System.out.println("[Парсинг информации о пользователе] - Код: " + responseCode);

            JSONArray myResponse = new JSONArray(response.toString());
            int i = 0;
            while (i < myResponse.length()) {
                JSONObject info = myResponse.getJSONObject(i);
                System.out.println(info.getLong("id"));
                System.out.println(userid);
                if (info.getLong("id") == userid) {
                    UserToEdit.setUsername(info.getString("username"));
                    UserToEdit.setType(info.getString("type"));
                    UserToEdit.setAdress(info.getString("adress"));
                    UserToEdit.setName(info.getString("name"));
                    UserToEdit.setLastname(info.getString("lastname"));
                    UserToEdit.setEmail(info.getString("email"));
                    return true;
                }
                i++;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
