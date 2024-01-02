package utils;

import java.util.UUID;

public class RandomAccountNumber {
    public static long create() {
        return (long) Math.abs(UUID.randomUUID().getMostSignificantBits() % 1e8);
    }
}
