package utils;

import java.util.UUID;

public class RandomTransactionId {
    public static String create() {
        return UUID.randomUUID().toString();
    }
}
