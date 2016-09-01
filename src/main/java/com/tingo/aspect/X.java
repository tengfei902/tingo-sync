package com.tingo.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 16/9/1.
 */
public class X {

    public static final String key = "qwwertyydq";

    public static boolean verify(String text) throws Exception {

        if(null == text || "".equals(text)) {
            return false;
        }

        m m1 = new m(key);
        String str = m1.decrypt(text);
        Date date = new SimpleDateFormat("yyyyMMdd").parse(str);
        return new Date().compareTo(date)<=0;
    }
}
