package com.opdup.btcwallet;

import com.opdup.btcrserviceclient.BTCRDIDResolver;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;
//import static org.mockito.mock.*;

public class BTCRDIDResolverTest  {

    private String BtcrDidString = "did:btcr:x705-jzv2-qqaz-7vuz"; //https://github.com/w3c-ccg/did-hackathon-2018/blob/master/BTCR-DID-Tests.md
    private String address = "";

    private BTCRDIDResolver tester;

    {
        try {
            tester = new BTCRDIDResolver(this.BtcrDidString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBtcrDidResolve() throws IOException {
        String string = tester.getBtcrDidResolve();
        assertFalse(string.isEmpty());
    }

    @Test
    public void getTip() throws IOException {
        String string = tester.getTip();
        assertFalse(string.isEmpty());
    }

    @Test
    public void decode() throws IOException {
        String string = tester.decode();
        assertFalse(string.isEmpty());
    }

    @Test
    public void txIdFromTxref() throws IOException, JSONException {
        String string = tester.txIdFromTxref();
        assertFalse(string.isEmpty());
    }

    @Test
    public void getDecodedTx() throws IOException, JSONException {
        String string = tester.getDecodedTx();
        assertFalse(string.isEmpty());
    }

    @Test
    public void getUtxos() throws IOException, JSONException {
        String string = tester.getUtxosForAddress(this.address);
        assertFalse(string.isEmpty());
    }
}