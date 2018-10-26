package btcr_service_client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class TxIdFromTxRef {

    private String url;

    public TxIdFromTxRef(String url){
        this.url = url;
    }

    public String getTxIdFromTxRef() throws IOException{
        String jsonString = new ServiceConnection(this.url).getJsonString();
        JSONObject jsonObject = new JSONObject(jsonString);
        String txid = null;
        if (jsonObject!=null){
            txid = jsonObject.getString("txid");
        }
        return txid;
    }

}