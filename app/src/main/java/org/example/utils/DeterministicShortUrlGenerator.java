package org.example.utils;


import java.math.BigInteger;
import java.security.MessageDigest;

public class DeterministicShortUrlGenerator {

    private static final String BASE62 =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generate(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());

            BigInteger number = new BigInteger(1, hash);
            StringBuilder sb = new StringBuilder();

            while (number.compareTo(BigInteger.ZERO) > 0 && sb.length() < 8) {
                sb.append(BASE62.charAt(
                        number.mod(BigInteger.valueOf(62)).intValue()));
                number = number.divide(BigInteger.valueOf(62));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hash generation failed", e);
        }
    }
}
