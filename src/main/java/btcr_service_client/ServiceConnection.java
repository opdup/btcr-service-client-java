package btcr_service_client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ServiceConnection {

    private String urlStr;

    public ServiceConnection(String url){
        this.urlStr = url;
    }

    public String getJson() throws IOException {
        String json = null;
        URL url = new URL(this.urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("charset", "utf-8");
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        json = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return json;
    }

}
