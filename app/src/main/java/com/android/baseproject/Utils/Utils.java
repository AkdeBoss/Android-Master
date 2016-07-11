package com.android.baseproject.Utils;

import java.util.UUID;

/**
 * Created by prajwalrai on 09/07/16.
 */
public class Utils {
    public static String generateRandomString() {
        String key = UUID.randomUUID().toString();
        key = key.replace("-", "");
        // Logger.error("Secret Key: " + key);
        return key;
    }
}
