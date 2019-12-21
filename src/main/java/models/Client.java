package models;

import algorithms.Euclid;
import generateprime.FindPrime;

import java.math.BigInteger;

import rabin.RabinService;

public class Client {
    private RabinService clientService;

    public Client() {
        clientService = ClientServiceImpl.construct();
        clientService.generateKeys();
    }

    public BigInteger encrypt(Server.KeyCarrier keyCarrier, BigInteger message) {
        return clientService.encrypt(message, keyCarrier.publicExponent, keyCarrier.publicModulus);
    }

    public String encrypt(Server.KeyCarrier keyCarrier, String message, int base) {
        return encrypt(keyCarrier, new BigInteger(message, base)).toString(base);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return clientService.decrypt(encryptedMessage);
    }

    public String decrypt(String encryptedMessage, int base) {
        return decrypt(new BigInteger(encryptedMessage, base)).toString(base);
    }

    public BigInteger signMessage(BigInteger messageToSign) {
        return clientService.signMessage(messageToSign);
    }

    public String signMessage(String messageToSign, int base) {
        return signMessage(new BigInteger(messageToSign, base)).toString(base);
    }

    public boolean verifySignMessage(String message,
                                     String signature,
                                     int base,
                                     Server.KeyCarrier keyCarrier) {
        return verifySignMessage(keyCarrier, new BigInteger(message, base),
                new BigInteger(signature, base));
    }

    private boolean verifySignMessage(Server.KeyCarrier keyCarrier, BigInteger message,
                                      BigInteger signature) {
        return clientService.verifyMessageSignature(message,
                signature,
                keyCarrier.publicExponent,
                keyCarrier.publicModulus);
    }

    public String getModulus(int base) {
        return getModulus().toString(base);
    }

    public BigInteger getModulus() {
        return clientService.getModulus();
    }

    private static class ClientServiceImpl extends RabinService {
        private BigInteger publicModulus;
        private BigInteger publicExponent;
        private BigInteger privateD;

        private static final BigInteger BYTE = BigInteger.valueOf(255);

        @Override
        public BigInteger encrypt(BigInteger message, BigInteger publicKey, BigInteger mod) {
            return message.modPow(publicKey, mod);
        }

        @Override
        public BigInteger decrypt(BigInteger encryptedMessage) {
            return encryptedMessage.modPow(privateD, publicModulus);
        }

        @Override
        public BigInteger signMessage(BigInteger message) {
            return message.modPow(privateD, publicModulus);
        }

        @Override
        public boolean verifyMessageSignature(BigInteger message,
                                              BigInteger signature,
                                              BigInteger publicKey,
                                              BigInteger mod) {
            return signature.modPow(publicKey, mod).equals(message);
        }

        private BigInteger generateModulus(int bits) {
            BigInteger primeP = FindPrime.findPrime(bits);
            BigInteger primeQ;
            do {
                primeQ = FindPrime.findPrime(bits);
            } while (primeP.equals(primeQ));
            this.publicModulus = primeP.multiply(primeQ);
            return primeP.subtract(BigInteger.ONE)
                    .multiply(primeQ.subtract(BigInteger.ONE));
        }

        private void generateExponentAndD(BigInteger eulerFunc) {
            int numberOfBits = 10;
            do {
                this.publicExponent = FindPrime.findPrime(10);
                numberOfBits++;
            } while (!this.publicExponent.gcd(eulerFunc).equals(BigInteger.ONE));
            this.privateD = Euclid.modInverse(this.publicExponent, eulerFunc);
        }

        @Override
        public void generateKeys() {
            generateExponentAndD(generateModulus(256));
        }

        @Override
        public void generateKeys(int bits) {
            if (bits < 256) {
                bits = 256;
            }
            generateExponentAndD(generateModulus(bits));
        }

        @Override
        public BigInteger transformMessage(byte[] bytes) {
            BigInteger returnValue = BigInteger.valueOf(bytes[0]);
            for (int i = 1; i < bytes.length; i++) {
                returnValue = returnValue.shiftLeft(8);
                returnValue = returnValue.or(BigInteger.valueOf(bytes[i]));
            }
            return returnValue;
        }

        @Override
        public BigInteger transformMessage(String msg) {
            return transformMessage(msg.getBytes());
        }

        @Override
        public BigInteger formatMessage(BigInteger message) {
            if (message.compareTo(publicModulus.sqrt()) > 0) {
                return message;
            }
            return message;
        }

        @Override
        public BigInteger getModulus() {
            return publicModulus;
        }

        static RabinService construct() {
            return new ClientServiceImpl();
        }
    }
}
