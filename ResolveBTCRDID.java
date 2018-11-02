package btcr_service_client;

import java.io.IOException;

public class ResolveBTCRDID {

    private String url;

    public ResolveBTCRDID(String url){
        this.url = url;
    }

    public String resolve() throws IOException {
        ServiceConnection serviceConnection = new ServiceConnection(this.url);
        return serviceConnection.getJsonString();
    }

}