package com.tingo.dto;

/**
 * Created by user on 16/8/24.
 */
public class SyncFieldDTO {
    private Long id;
    private Long originTableId;
    private String originTablename;
    private String originField;
    private String originType;
    private String targetField;
    private String targetType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOriginTableId() {
        return originTableId;
    }

    public void setOriginTableId(Long originTableId) {
        this.originTableId = originTableId;
    }

    public String getOriginTablename() {
        return originTablename;
    }

    public void setOriginTablename(String originTablename) {
        this.originTablename = originTablename;
    }

    public String getOriginField() {
        return originField;
    }

    public void setOriginField(String originField) {
        this.originField = originField;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}
