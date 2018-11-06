package btcr_service_client;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class BTCRDIDResolverTest {

    private String BtcrDidString = "did:btcr:x705-jzv2-qqaz-7vuz"; //https://github.com/w3c-ccg/did-hackathon-2018/blob/master/BTCR-DID-Tests.md
    private BTCRDIDResolver tester = new BTCRDIDResolver(this.BtcrDidString);

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
    public void txIdFromTxref() throws IOException{
        String string = tester.txIdFromTxref();
        assertFalse(string.isEmpty());
    }

    @Test
    public void getDecodedTx() throws IOException {
        String string = tester.getDecodedTx();
        assertFalse(string.isEmpty());
    }

    @Test
    public void getUtxos() throws IOException {
        String string = tester.getUtxos();
        assertFalse(string.isEmpty());
    }
}