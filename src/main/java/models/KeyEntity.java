package models;

import algorithms.JacobiSymbol;

import java.math.BigInteger;

public class KeyEntity {
    private BigInteger encryptedMessage;
    private BigInteger publicModulus;

    private boolean parityBit;
    private boolean indicatorJacobiSymbol;

    private String hexEncryptedMessage;
    private String hexPublicModulus;

    public KeyEntity(BigInteger encryptedMessage, BigInteger publicModulus) {
        this.encryptedMessage = encryptedMessage;
        this.publicModulus = publicModulus;
        computeParameters(this);
    }

    public KeyEntity(BigInteger encryptedMessage, BigInteger publicModulus, int base) {
        this(encryptedMessage, publicModulus);
        this.hexEncryptedMessage = encryptedMessage.toString(base);
        this.hexPublicModulus = publicModulus.toString(base);
        computeParameters(this);
    }

    private static void computeParameters(KeyEntity keyEntity) {
        keyEntity.parityBit = keyEntity.encryptedMessage.testBit(0);
        keyEntity.indicatorJacobiSymbol = JacobiSymbol
                .computeJacobiSymbol(keyEntity.encryptedMessage, keyEntity.publicModulus) == 1;
    }

    public KeyEntity toBase(int base) {
        if (hexEncryptedMessage == null || hexPublicModulus == null) {
            this.hexEncryptedMessage = encryptedMessage.toString(base);
            this.hexPublicModulus = publicModulus.toString(base);
        }
        return this;
    }
}
