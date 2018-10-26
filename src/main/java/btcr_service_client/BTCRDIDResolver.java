package btcr_service_client;

import java.io.IOException;

public class BTCRDIDResolver {

    private String brtcrDid;
    private String txRef;

    public BTCRDIDResolver(String brtcrDid){
        this.brtcrDid = brtcrDid;
        this.txRef = this.brtcrDid.substring(9);
    }

    // Resolve BTCR DID
    public String getBrtcrDidRes() throws IOException {
        String url = "https://localhost:8080/txref/"+ this.txRef +"/resolve";
        ResolveBTCRDID resolveBTCRDID = new ResolveBTCRDID(url);
        String btrdidRes = resolveBTCRDID.resolve();
        return btrdidRes;
    }

    // Following a tip
    public String getTip() throws IOException {
        String url = "https://localhost:8080/txref"+ this.txRef +"/tip";
        return new Tip(url).getTip();
    }

    // Decode TxRef
    public String decode() throws IOException {
        String url = "https://localhost:8080/txref"+ this.txRef +"/decode";
        return new Decode(url).decode();
    }

    // Get TxId from TxRef
    public String txIdFromTxref() throws IOException {
        String url = "https://localhost:8080/txref"+ this.txRef +"/txid";
        return new TxIdFromTxRef(url).getTxIdFromTxRef();
    }

    // Get Decoded Tx from TxId
    public String getDecodedTx() throws IOException {
        String txId = txIdFromTxref();
        String url = "https://localhost:8080/tx"+ txId;
        return new DecodedTx(url).getTxFromTxId();
    }

    //Txid to Utxos for the address in Txid
    public String getUtxos() throws IOException {
        //how to get the address???
    }

}
