package client.controllers.checkConnection;

import java.net.HttpURLConnection;
import java.net.URL;

public class check {

    public boolean checkCon(){
        try{
            URL obj = new URL("http://localhost:8080/users");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.getResponseCode();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
