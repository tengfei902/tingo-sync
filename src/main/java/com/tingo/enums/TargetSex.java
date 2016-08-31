package com.tingo.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by tengfei on 16/9/1.
 */
public enum  TargetSex {
    male("0"),female("1");

    private String value;

    TargetSex(String value) {
        this.value = value;
    }

    public static TargetSex parse(String value) {
        if(StringUtils.isEmpty(value)) {
            return null;
        }

        for(TargetSex sex:TargetSex.values()) {
            if(StringUtils.equals(value,sex.getValue())) {
                return sex;
            }
        }

        return null;
    }

    public String getValue() {
        return this.value;
    }
}
