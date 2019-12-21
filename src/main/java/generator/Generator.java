package generator;

import java.math.BigInteger;

public interface Generator {
    BigInteger getRandom(BigInteger start, BigInteger end);
}
