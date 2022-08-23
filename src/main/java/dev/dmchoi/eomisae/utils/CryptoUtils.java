package dev.dmchoi.eomisae.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtils {
    public enum Hash {
        MD5("MD5"),
        SHA_256("SHA-256"),
        SHA_512("SHA-512");
        public final String algorithm;
        Hash(String algorithm) {
            this.algorithm = algorithm;
        }
    }

    public static String hash(Hash hash, String input) {
        return CryptoUtils.hash(hash, input, StandardCharsets.UTF_8, null);
    }

    public static String hash(Hash hash, String input, String fallback) {
        return CryptoUtils.hash(hash, input, StandardCharsets.UTF_8, fallback);
    }

    public static String hash(Hash hash, String input, Charset charset, String fallback) {
        try {
            return CryptoUtils.hashUnsafe(hash, input, charset);
        } catch (NoSuchAlgorithmException ignored) {
            return fallback;
        }
    }

    public static String hashUnsafe(Hash hash, String input) throws NoSuchAlgorithmException {
        return CryptoUtils.hashUnsafe(hash, input, StandardCharsets.UTF_8);
    }

    public static String hashUnsafe(Hash hash, String input, Charset charset) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(hash.algorithm);
        messageDigest.reset();
        messageDigest.update(input.getBytes(charset));
        StringBuilder outputBuilder = new StringBuilder();
        for (byte hashByte : messageDigest.digest()) {
            outputBuilder.append(String.format("%02x", hashByte));
        }
        return outputBuilder.toString();
    }

    private CryptoUtils() {

    }
}