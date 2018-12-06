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
    public String resolve() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/resolve");
        /*
            1. mock to return json from https://btcr-service.opdup.com/txref/txtest1:x705-jzv2-qqaz-7vuz/txid
            2. get the tx details from https://btcr-service.opdup.com/tx/<txid>/txid
            3. Check by hitting /tip, if there is a tip, if there isn't on then go to 4, else goto 5
            4. From the transaction received, find the pubkey in the scriptSig
            5. Show that the DID is revoked

         */
        return new Resolve(this.endpoint).resolve();
    }

    // Get Tx Details
    public String getTxDetails() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/txid");
        return new TxDetails(this.endpoint).getTxDetails();
    }

    // Following a tip
    public String getTip() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/tip");
        return new Tip(this.endpoint).getTip();
    }

    // Get public key following a tip
    public String getPublicKey() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/tip");
        return new Tip(this.endpoint).getPubKey();
    }

    // Decode TxRef
    public String decode() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/decode");
        return new Decode(this.endpoint).decode();
    }

    // Get TxId from TxRef
    public String txIdFromTxref() throws IOException, JSONException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/txid");
        return new TxDetails(this.endpoint).getTxIdFromTxRef();
    }

    // Get Decoded Tx from TxId
    public String getDecodedTx() throws IOException, JSONException {
        String txId = txIdFromTxref();
        this.endpoint = new URL(this.root, "tx/" + txId);
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
