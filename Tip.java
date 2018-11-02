package btcr_service_client;

import java.io.IOException;

public class Tip {

    private String url;

    public Tip(String url){
        this.url = url;
    }

    public String getTip() throws IOException{
        return new ServiceConnection(this.url).getJsonString();
    }

}