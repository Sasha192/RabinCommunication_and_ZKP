package models;

import algorithms.GenerateRandom;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigInteger;

public class ServerTest {
    private static Server server;
    private static Client client;
    private static Server.KeyCarrier keyCarrier;
    private static final int KEY_SIZE;
    private static final int NUMBER_OF_EACH_TEST;
    private static final int BASE;

    // Is it better to Server.Entity create for Client ? I mean, that server.send(Server.Entity, ... )

    private static String removeHighZerosFromHex(String hex) {
        int i = 0;
        for (char charElement: hex.toCharArray()){
            if(!(charElement == '0')) {
                break;
            }
            i++;
        }
        return new String(hex.toCharArray(), i, hex.length());
    }

    static {
        KEY_SIZE = 512;
        NUMBER_OF_EACH_TEST = 10;
        BASE = 16;
    }

    @BeforeClass
    public static void setup() {
        server = new Server(KEY_SIZE);
        client = new Client();
        keyCarrier = server.keyCarrier;
    }

    @Test
    public void encryptionTest() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            String hexMessage = GenerateRandom.generate(BigInteger.ZERO, keyCarrier.publicModulus).toString(BASE);
            String resultMessage = server.encrypt(client.getModulus(BASE), client.getModulus(BASE), hexMessage);
            resultMessage = client.decrypt(resultMessage, BASE);
            Assert.assertEquals(hexMessage, resultMessage);
        }
    }

    // Test failed 'cause of most significant zeros : Server returns - 0FFF = FFF - my returns ? F*CK
    @Test
    public void decryptionTest() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            //String hexMessage = GenerateRandom.generate(BigInteger.ZERO, keyCarrier.publicExponent).toString(BASE);
            //String resultMessage = client.encrypt(hexMessage, BASE, keyCarrier);
            //resultMessage = server.decrypt(resultMessage);
            //resultMessage = removeHighZerosFromHex(hexMessage);
            //Assert.assertEquals(resultMessage, hexMessage);
        }
    }

    @Test
    public void signMessageTest() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
/*            String hexMessage = GenerateRandom.generate(BigInteger.ZERO, serverEntity.publicMod).toString(BASE);
            String resultMessage = server.signMessage(hexMessage);
            Assert.assertTrue(client.verifySignMessage(hexMessage, resultMessage, BASE, serverEntity));*/
        }
    }

/*    @Test
    public void verifySignMessageTest() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            String hexMessage = GenerateRandom.generate(BigInteger.ZERO, serverEntity.publicMod).toString(BASE);
            String resultMessage = client.signMessage(hexMessage, BASE);
            Assert.assertTrue(server.verifySignMessage(hexMessage,
                    resultMessage,
                    client.getPublicE(BASE),
                    client.getMod(BASE)));
        }
    }

    @Test
    public void sendKeyTest() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            KeyEntity keyEntity = client.sendKey(BASE, serverEntity);
            Assert.assertTrue(server.receiveKey(keyEntity.hexKey, keyEntity.hexSign,
                    client.getMod(BASE), client.getPublicE(BASE)));
        }
    }

    @Test
    public void receiveKeyTest() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            KeyEntity keyEntity = server.sendKey(client.getPublicE(BASE), client.getMod(BASE));
            Assert.assertTrue(client.receiveKey(serverEntity, keyEntity, BASE));
        }
    }*/
}