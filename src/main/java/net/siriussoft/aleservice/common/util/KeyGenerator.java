package net.siriussoft.aleservice.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class KeyGenerator {

    public static String uuidHash(EncAlgorithm algorithm) {
        String hash = "";

        try {
            String uuid = UUID.randomUUID().toString();

            MessageDigest sha256 = MessageDigest.getInstance(algorithm.toString());
            byte[] result = sha256.digest(uuid.getBytes());

            hash = hexEncode(result);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        return hash;
    }

    private static String hexEncode(byte[] input) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[input.length * 2];

        for (int idx = 0; idx < input.length; idx++) {
            int volk = input[idx] & 0xFF;
            hexChars[idx * 2] = hexArray[volk >>> 4];
            hexChars[idx * 2 + 1] = hexArray[volk & 0x0F];
        }

        return new String(hexChars);
    }
}