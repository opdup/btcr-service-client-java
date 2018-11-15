package com.opdup.btcrserviceclient;

import java.io.IOException;
import java.net.URL;

public class Tip {

    private URL url;

    public Tip(URL url){
        this.url = url;
    }

    public String getTip() throws IOException{
        return new ServiceConnection(this.url).getJsonString();
    }

}