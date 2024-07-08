package it.mikeslab.util;

import java.util.Base64;

public class Base64Util {

    public static String decode(String encodedContent) {

        byte[] decodedBytes = Base64.getDecoder().decode(encodedContent);

        return new String(decodedBytes, java.nio.charset.StandardCharsets.UTF_8);
    }


}
