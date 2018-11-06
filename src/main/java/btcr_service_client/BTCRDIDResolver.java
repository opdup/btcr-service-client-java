package btcr_service_client;

import java.io.IOException;

public class BTCRDIDResolver {

    private String brtcrDid;
    private String txRef;

    private String PROTOCOL = "https://";
    private String ADDRESS = "localhost";
    private String PORT = "8080";
    private int TX_REF_SUBSTRING = 9;

    //Constructor
    public BTCRDIDResolver(String brtcrDid){
        this.brtcrDid = brtcrDid;
        this.txRef = this.brtcrDid.substring(TX_REF_SUBSTRING);
    }


    // Resolve BTCR DID
    public String getBtcrDidResolve() throws IOException {
        String url = PROTOCOL + ADDRESS + ":" + PORT + "/txref/" + this.txRef + "/resolve";
        return new ResolveBTCRDID(url).resolve();
    }

    // Following a tip
    public String getTip() throws IOException {
        String url = PROTOCOL + ADDRESS + ":" + PORT + "/txref/" + this.txRef + "/tip";
        return new Tip(url).getTip();
    }

    // Decode TxRef
    public String decode() throws IOException {
        String url = PROTOCOL + ADDRESS + ":" + PORT + "/txref/" + this.txRef + "/decode";
        return new Decode(url).decode();
    }

    // Get TxId from TxRef
    public String txIdFromTxref() throws IOException {
        String url = PROTOCOL + ADDRESS + ":" + PORT + "/txref/" + this.txRef + "/txid";
        return new TxIdFromTxRef(url).getTxIdFromTxRef();
    }

    // Get Decoded Tx from TxId
    public String getDecodedTx() throws IOException {
        String txId = txIdFromTxref();
        String url = PROTOCOL + ADDRESS + ":" + PORT + "/tx" + txId;
        return new DecodedTx(url).getTxFromTxId();
    }

    //Txid to Utxos for the address in Txid
    public String getUtxos() throws IOException {
        String txId = txIdFromTxref();
        String addr = "";
        String url = PROTOCOL + ADDRESS + ":" + PORT + "/addr/" + addr + "/spends";
        String utxo = "";
        return "";
    }

}
