package com.opdup.btcrserviceclient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ServiceConnection {

    private URL url;
    private HttpURLConnection connection;

    public ServiceConnection(URL url) throws IOException{
        this.url = url;
    }


    public void connect() throws IOException {
        this.connection = (HttpURLConnection) this.url.openConnection();
        this.connection.setDoOutput(true);
        this.connection.setInstanceFollowRedirects(false);
        this.connection.setRequestMethod("GET");
        this.connection.setRequestProperty("Content-Type", "application/json");
        this.connection.setRequestProperty("charset", "utf-8");
        this.connection.connect();
    }

    public String getJsonString() throws IOException {
        String json = null;
        connect();
        InputStream inputStream = connection.getInputStream();
        json = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return json;
    }

    public JSONObject getJsonObject() throws IOException, JSONException {
        return new JSONObject(getJsonString());
    }

}