package br.com.daysofcode.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static String getHashMd5(String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, messageDigest.digest(value.getBytes()));
            return hash.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            throw  new RuntimeException(ex);
        }
    }

}
