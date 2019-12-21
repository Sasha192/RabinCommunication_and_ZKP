package rabin;

import java.math.BigInteger;

public interface ClientService {
    BigInteger encrypt(BigInteger message, BigInteger publicKey, BigInteger mod);

    BigInteger decrypt(BigInteger encryptedMessage);

    BigInteger signMessage(BigInteger message);

    boolean verifyMessageSignature(BigInteger message,
                                      BigInteger signature,
                                      BigInteger publicKey,
                                   BigInteger mod);

    void generateKeys();

    void generateKeys(int bits);

    BigInteger transformMessage(byte[] bytes);

    BigInteger transformMessage(String msg);
}
