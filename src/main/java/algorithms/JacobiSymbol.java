package algorithms;

import java.math.BigInteger;

public class JacobiSymbol {
    private static final BigInteger THREE;
    private static final BigInteger EIGHT;
    private static final BigInteger FOUR;
    private static final BigInteger SEVEN;

    static {
        THREE = BigInteger.valueOf(3);
        EIGHT = BigInteger.valueOf(8);
        FOUR = BigInteger.valueOf(4);
        SEVEN = BigInteger.valueOf(7);
    }

    public static int computeJacobiSymbol(BigInteger x, BigInteger modulus) {
        BigInteger a = x.mod(modulus);
        if (a.equals(BigInteger.ONE) || modulus.equals(BigInteger.ONE)) {
            return 1;
        }
        if (a.equals(BigInteger.ZERO)) {
            return 0;
        }
        int e = 0;
        BigInteger oddA = a;
        while (oddA.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
            e++;
            oddA = oddA.divide(BigInteger.TWO);
        }
        int s;
        if (e % 2 == 0) {
            s = 1;
        } else {
            BigInteger modulusModEight = modulus.mod(EIGHT);
            if (modulusModEight.equals(BigInteger.ONE) || modulusModEight.equals(SEVEN)) {
                s = 1;
            } else {
                s = -1;
            }
        }
        if (modulus.mod(FOUR).equals(THREE) && oddA.mod(FOUR).equals(THREE)) {
            s = -s;
        }
        BigInteger modulusModOddA = modulus.mod(oddA);
        return s * computeJacobiSymbol(modulusModOddA, oddA);
    }
}
