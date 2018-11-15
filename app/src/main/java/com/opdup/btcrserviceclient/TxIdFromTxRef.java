package com.opdup.btcrserviceclient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class TxIdFromTxRef {

    private URL url;

    public TxIdFromTxRef(URL url){
        this.url = url;
    }

    public String getTxIdFromTxRef() throws IOException, JSONException {
        String jsonString = new ServiceConnection(this.url).getJsonString();
        JSONObject jsonObject = new JSONObject(jsonString);
        String txid = null;
        if (jsonObject != null){
            txid = jsonObject.getString("txid");
        }
        return txid;
    }

}