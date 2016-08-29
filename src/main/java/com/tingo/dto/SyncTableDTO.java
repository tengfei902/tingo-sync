package com.tingo.dto;

import com.tingo.enums.SyncType;

/**
 * Created by user on 16/8/24.
 */
public class SyncTableDTO {
    private Long id;
    private String originTable;
    private String targetTable;
    private SyncType syncType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginTable() {
        return originTable;
    }

    public void setOriginTable(String originTable) {
        this.originTable = originTable;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public SyncType getSyncType() {
        return syncType;
    }

    public void setSyncType(SyncType syncType) {
        this.syncType = syncType;
    }
}
