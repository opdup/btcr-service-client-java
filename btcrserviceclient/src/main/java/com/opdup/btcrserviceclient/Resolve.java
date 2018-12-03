package com.opdup.btcrserviceclient;

import java.io.IOException;
import java.net.URL;

public class ResolveBTCRDID {

    private URL url;

    public ResolveBTCRDID(URL url){
        this.url = url;
    }

    public String resolve() throws IOException {
        ServiceConnection serviceConnection = new ServiceConnection(this.url);
        return serviceConnection.getJsonString();
    }

}