package btcr_service_client;

import java.io.IOException;

public class Decode {

    private String url;

    public Decode(String url){
        this.url = url;
    }

    public String decode() throws IOException{
        return new ServiceConnection(this.url).getJsonString();
    }

}