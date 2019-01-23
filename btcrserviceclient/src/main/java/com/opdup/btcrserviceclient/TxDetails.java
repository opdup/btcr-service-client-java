package com.opdup.btcrserviceclient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class TxDetails {

    private URL url;

    private String jsonString;
    private JSONObject jsonObject;
    private String txId;

    public TxDetails(URL url) throws IOException{
        this.url = url;
        this.jsonString = new ServiceConnection(this.url).getJsonString();
    }

    public String getTxDetails() {
        //this.jsonString = new ServiceConnection(this.url).getJsonString();
        return this.jsonString;
    }

    public String getTxIdFromTxRef() throws JSONException {
        //this.jsonString = new ServiceConnection(this.url).getJsonString();
        this.jsonObject = new JSONObject(this.jsonString);
        this.txId = null;
        if (jsonObject != null){
            this.txId = jsonObject.getString("txid");
        }
        return this.txId;
    }

}