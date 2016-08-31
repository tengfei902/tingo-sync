package com.tingo.utils;

import com.tingo.enums.OriginSex;
import com.tingo.enums.TargetSex;

/**
 * Created by tengfei on 16/9/1.
 */
public class Converter {

    public static String convertSex(String originSexValue) {
        OriginSex originSex = OriginSex.parse(originSexValue);
        if(null == originSex) {
            return null;
        }

        switch (originSex) {
            case male:
                return TargetSex.male.getValue();
            case female:
                return TargetSex.female.getValue();
            default:
                return null;
        }
    }
}
