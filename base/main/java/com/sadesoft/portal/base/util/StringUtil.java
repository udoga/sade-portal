package com.sadesoft.portal.base.util;

import com.sadesoft.portal.base.error.InvalidNumberError;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

public class StringUtil {
    public static Integer getInteger(String value) {
        try { return Integer.parseInt(value); }
        catch (NumberFormatException e) { throw new InvalidNumberError(value); }
    }

    public static String hashSha512(String s) {
        MessageDigest digest = call(() -> MessageDigest.getInstance("SHA-512"));
        byte[] encodedHash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    public static String convertTurkishToAscii(String input) {
        String[][] pairs = {{"ç", "c"}, {"Ç", "C"}, {"ğ", "g"}, {"Ğ", "G"}, {"ı", "i"}, {"İ", "I"}, {"ö", "o"},
                {"Ö", "O"}, {"ş", "s"}, {"Ş", "S"}, {"ü", "u"}, {"Ü", "U"}};
        for (String[] pair : pairs) input = input.replace(pair[0], pair[1]);
        return input;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) hex.append(byteToHex(b));
        return hex.toString();
    }

    private static String byteToHex(byte b) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) hex = "0" + hex;
        return hex;
    }

    private static <V> V call(Callable<V> callable) {
        try { return callable.call(); }
        catch (RuntimeException e) { throw e; }
        catch (Exception e) { throw new RuntimeException(e); }
    }
}
