package com.opdup.btcrserviceclient;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BTCRDIDResolver {

    private String btcrDid;
    private String txRef;

    private URL root;
    private URL endpoint;
    private String PROTOCOL = "https";
    private String ADDRESS = "localhost";
    private String PORT = "8080";
    private int TX_REF_SUBSTRING = 9;

    //Constructor
    public BTCRDIDResolver(String btcrDid) throws MalformedURLException {
        this.root = new URL(this.PROTOCOL, this.ADDRESS, this.PORT);
        this.btcrDid = btcrDid;
        this.txRef = getTxRef(btcrDid);
    }

    public BTCRDIDResolver(String btcrDid, URL rootURl) throws MalformedURLException {
        this.root = rootURl;
        this.btcrDid = btcrDid;
        this.txRef = getTxRef(btcrDid);
    }

    // Resolve BTCR DID
    public String getBtcrDidResolve() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/resolve");
        //String url = PROTOCOL + ADDRESS + ":" + PORT + "/txref/" + this.txRef + "/resolve";
        return new ResolveBTCRDID(this.endpoint).resolve();
    }

    // Following a tip
    public String getTip() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/tip");
        //String url = PROTOCOL + ADDRESS + ":" + PORT + "/txref/" + this.txRef + "/tip";
        return new Tip(this.endpoint).getTip();
    }

    // Decode TxRef
    public String decode() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/decode");
        //String url = PROTOCOL + ADDRESS + ":" + PORT + "/txref/" + this.txRef + "/decode";
        return new Decode(this.endpoint).decode();
    }

    // Get TxId from TxRef
    public String txIdFromTxref() throws IOException, JSONException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/txid");
        //String url = PROTOCOL + ADDRESS + ":" + PORT + "/txref/" + this.txRef + "/txid";
        return new TxIdFromTxRef(this.endpoint).getTxIdFromTxRef();
    }

    // Get Decoded Tx from TxId
    public String getDecodedTx() throws IOException, JSONException {
        String txId = txIdFromTxref();
        this.endpoint = new URL(this.root, "tx/" + txId);
        //String url = PROTOCOL + ADDRESS + ":" + PORT + "/tx" + txId;
        return new DecodedTx(this.endpoint).getTxFromTxId();
    }

    //Txid to Utxos for the address in Txid
    public String getUtxosForAddress(String address) throws IOException {
        this.endpoint = new URL(this.root, "addr/" + address + "/spends");
        return new UtxosForAddress(this.endpoint).getUtxos();
    }


    //Get the TxRef from BTCR DID
    private String getTxRef(String btcrDid){
        return btcrDid.substring(TX_REF_SUBSTRING);
    }

}
