package btcr_service_client;

import java.io.IOException;
import org.json.JSONObject;

public class DecodedTx {

    private String url;

    public DecodedTx(String url){
        this.url = url;
    }

    public String getTxFromTxId() throws IOException{
        ServiceConnection serviceConnection = new ServiceConnection(this.url);
        return serviceConnection.getJsonString();
    }

}
