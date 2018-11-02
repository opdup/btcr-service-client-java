import btcr_service_client.BTCRDIDResolver;

import java.io.IOException;

public class TestBTCRDIDResolver {

    public static void main(String[] args) throws IOException {
        String btcrDid="";
        BTCRDIDResolver resolver = new BTCRDIDResolver(btcrDid);
        System.out.println("Resolve BTCR DID\n" + resolver.getBrtcrDidRes());
        System.out.println("\n\nFollowing a Tip\n" + resolver.getTip());
        System.out.println("\n\nDecode a Txref\n" + resolver.decode());
        System.out.println("\n\nGet Txid from Txref\n" + resolver.txIdFromTxref());
        System.out.println("\n\nGet decoded Tx from Txid" + resolver.getDecodedTx());
    }

}
