package com.tingo.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by tengfei on 16/9/1.
 */
public enum OriginSex {
    male("1"),female("2");

    private String value;

    OriginSex(String value) {
        this.value = value;
    }

    public static OriginSex parse(String value) {
        if(StringUtils.isEmpty(value)) {
            return null;
        }

        for(OriginSex sex:OriginSex.values()) {
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
