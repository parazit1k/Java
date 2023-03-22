package client.controllers.brand;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class POST {

    public boolean addBrand(String text) throws IOException {
        try {
            URL url = new URL("http://localhost:8080/brand");
            System.out.println("da");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            System.out.println("da");

            OutputStream os = connection.getOutputStream();
            os.write(text.getBytes(StandardCharsets.UTF_8));
            os.close();
            System.out.println("da");
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String result = IOUtils.toString(in, StandardCharsets.UTF_8);
            System.out.println("da");
            in.close();
            connection.disconnect();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
