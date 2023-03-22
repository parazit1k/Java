package client.controllers.addToList;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AddToProduct {
    public void deleteProduct(Long productId) throws IOException {
        URL url = new URL("http://localhost:8080/products?productId="+productId);
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

    public void addProduct(String text, String brand) throws IOException {
        URL url = new URL("http://localhost:8080/products?brandname="+brand);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");

        OutputStream os = connection.getOutputStream();
        os.write(text.getBytes(StandardCharsets.UTF_8));
        os.close();

        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);

        in.close();
        connection.disconnect();
    }

    public void editProduct(String text) throws IOException {
        URL url = new URL("http://localhost:8080/products");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("PUT");

        OutputStream os = connection.getOutputStream();
        os.write(text.getBytes(StandardCharsets.UTF_8));
        os.close();

        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);

        in.close();
        connection.disconnect();
    }

    public void orderProduct(String text) throws IOException {
        URL url = new URL("http://localhost:8080/products");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("PUT");

        OutputStream os = connection.getOutputStream();
        os.write(text.getBytes(StandardCharsets.UTF_8));
        os.close();

        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);

        in.close();
        connection.disconnect();
    }
}
