package com.opdup.btcrserviceclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public BTCRDIDResolver(String btcrDid, URL rootURl) {
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
            4. Fetch all transactions for the address in the txref by going to /resolve.
            4.1 From all the the transaction received, find the  one matching the txid received from 7txid
            4.2 Find the pubkey in the scriptSig using the regular expression used by uniresolver
            5. Show that the DID is revoked

         */
        return new Resolve(this.endpoint).resolve();
    }

    //Resolve returning JSONObject
    public JSONObject resolveJSONObject() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/resolve");
        return new Resolve(this.endpoint).resolveJSONObject();
    }

    //Resolve returning JSONArray
    public JSONArray resolveJSONArray() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/resolve");
        return new Resolve(this.endpoint).resolveJSONArray();
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

    // Decode TxRef
    public String decode() throws IOException {
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/decode");
        return new Decode(this.endpoint).decode();
    }

    //Get the TxId from the TxDetails class
    public String getTxId() throws IOException{
        this.endpoint = new URL(this.root, "txref/" + this.txRef + "/txid");
        return new TxDetails(this.endpoint).getTxIdFromTxRef();
    }

    // Get Decoded Tx from TxId
    public String getDecodedTx() throws IOException, JSONException {
        String txId = getTxId();
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
        return "txtest1:" + btcrDid.substring(TX_REF_SUBSTRING);
    }

    //Return public key
    public String getDDOForTxref() throws IOException {

        String pubKey = null;
        String txid = getTxId();
        String tip = getTip();
        String asm = null;

        if (tip != null){
            return "BTCR DID couldn't be resolved";
        }else{

            JSONArray allTxs = resolveJSONArray();
            JSONArray vin;

            JSONObject object;
            JSONObject txObject;
            JSONObject scriptSig = null;
            JSONObject vinObject = null;

            String txIdString = null;

            for (int i = 0; i < allTxs.length(); i++){

                object = allTxs.getJSONObject(i);
                txObject = object.getJSONObject("Transaction");
                txIdString = txObject.getString("txid");

                if (txIdString.equals(txid)){

                    vin = txObject.getJSONArray("vin");
                    for (int j = 0; j < vin.length(); j++){
                        vinObject = vin.getJSONObject(j);
                        scriptSig = vinObject.getJSONObject("scriptSig");
                    }

                    if (scriptSig == null){
                        return null;
                    }

                    asm = scriptSig.getString("asm");

                    if (asm == null){
                        return null;
                    }

                    String[] values = asm.split("\\s+");
                    pubKey = values[1];

                }

            }

        }

        return pubKey;
    }

}
