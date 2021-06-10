package client.controllers.orders;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class POST {

    public long insertInfo(Long id) throws IOException, JSONException {
        URL url = new URL("http://localhost:8080/orders?userid="+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");

        OutputStream os = connection.getOutputStream();
        os.write("{}".getBytes(StandardCharsets.UTF_8));
        os.close();

        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);

        in.close();
        connection.disconnect();

        JSONObject response = new JSONObject(result.toString());
        return response.getLong("id");
        }
    }

