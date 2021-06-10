package client.controllers.parsing;

import client.controllers.UserInfo.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParseGET {
    public boolean GET(String username, String password) throws IOException, JSONException {
        try {
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
            System.out.println("[Парсинг информации о пользователях] - Код: " + responseCode);

            JSONArray myResponse = new JSONArray(response.toString());
            int i = 0;
            while (i < myResponse.length()) {
                JSONObject info = myResponse.getJSONObject(i);
                if (username.equals(info.getString("username").toLowerCase()) && (password.equals(info.getString("password")))) {
                    User.setId(info.getLong("id"));
                    User.setUsername(info.getString("username"));
                    User.setType(info.getString("type"));
                    User.setAdress(info.getString("adress"));
                    User.setName(info.getString("name"));
                    User.setLastname(info.getString("lastname"));
                    User.setEmail(info.getString("email"));
                    User.setPassword(info.getString("password"));
                    return true;
                }
                i++;
            }
            return false;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
