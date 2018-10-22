package btcr_service_client;

import java.io.IOException;

public class TxIdFromTxRef {

    private String url;

    public TxIdFromTxRef(String url){
        this.url = url;
    }

    public String getTxIdFromTxRef() throws IOException{
        return new ServiceConnection(this.url).getJson();
    }

}
