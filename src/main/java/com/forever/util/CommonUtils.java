package com.forever.util;

import java.util.UUID;

/**
 * Created by Forever丶诺 on 2017-10-22.
 */
public class CommonUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
