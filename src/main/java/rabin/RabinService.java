package rabin;

import java.math.BigInteger;

public abstract class RabinService implements ClientService {
    public abstract BigInteger getModulus();

    public abstract BigInteger formatMessage(BigInteger message);
}
