package com.tingo.enums;

/**
 * Created by tengfei on 16/8/26.
 */
public enum StatusType {
    VALID(0),INVALID(1);

    private Integer value;

    StatusType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
