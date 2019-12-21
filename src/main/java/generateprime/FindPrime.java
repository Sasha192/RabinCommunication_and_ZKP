package generateprime;

import algorithms.GenerateRandom;
import algorithms.PrimarilyTest;
import java.math.BigInteger;
import java.security.SecureRandom;

public class FindPrime {
    private static final int CERTAINTY;
    private static final BigInteger FOUR;

    static {
        CERTAINTY = 5;
        FOUR = BigInteger.valueOf(4);
    }

    private static BigInteger run(BigInteger start, BigInteger end) {
        BigInteger randomX = GenerateRandom.generate(start, end);
        while (!PrimarilyTest.test(randomX, CERTAINTY)) {
            randomX = GenerateRandom.generate(start, end);
        }
        return randomX;
    }

    public static BigInteger findPrime(int numberOfBits) {
        BigInteger start  = BigInteger.ZERO;
        while (start.bitLength() < numberOfBits) {
            start = new BigInteger(numberOfBits, new SecureRandom());
        }
        return run(start, start.shiftLeft(2));
    }

    public static BigInteger findBlumPrime(int numberOfBits) {
        BigInteger prime = findPrime(numberOfBits);
        while (!prime.add(BigInteger.ONE).mod(FOUR).equals(BigInteger.ZERO)) {
            prime = findPrime(numberOfBits);
        }
        return prime;
    }
}
