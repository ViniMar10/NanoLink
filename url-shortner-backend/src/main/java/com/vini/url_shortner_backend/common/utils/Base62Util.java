package com.vini.url_shortner_backend.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Base62Util {
    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new SecureRandom();
    private static final int MIN_CHARS = 6;
    private static final int MAX_CHARS = 10;

    public static String randomChars(int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++){
            sb.append(CHARS.charAt(RANDOM.nextInt(62)));
        }
        return sb.toString();
    }

    public static String generateCode(){
        return randomChars(MIN_CHARS + RANDOM.nextInt(MAX_CHARS - MIN_CHARS + 1));
    }
}
