package com.opdup.btcrserviceclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.Scanner;

public class ServiceConnection {

    private URL url;
    private HttpURLConnection connection;
    private String json = null;

    public ServiceConnection(URL url) throws IOException{
        this.url = url;
    }

    private void connect()  {
        try {
            this.connection = (HttpURLConnection) this.url.openConnection();
            this.connection.setDoOutput(true);
            this.connection.setInstanceFollowRedirects(false);
            this.connection.setRequestMethod("GET");
            this.connection.setRequestProperty("Content-Type", "application/json");
            this.connection.setRequestProperty("charset", "utf-8");
            //this.connection.setConnectTimeout(5000);  //Connection timeout
            this.connection.connect();
        } catch (SocketException e) {
            System.err.print(e.getMessage());
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }

    public String getJsonString() throws IOException {
        connect();
        InputStream inputStream = connection.getInputStream();
        this.json = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        if (json == null){
            return "null";
        } else {
            return this.json;
        }
    }

    public JSONObject getJsonObject() throws IOException, JSONException {
        return new JSONObject(getJsonString());
    }

    public JSONArray getJsonArray() throws IOException {
        return new JSONArray(getJsonString());
    }

}