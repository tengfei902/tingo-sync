package com.tingo.sync;

import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/24.
 */
public interface DataSync {
    List<SyncLink> getSyncLinks(SyncType syncType);
    List<String> getSyncIds(SyncType syncType);
    void doSave(List<String> ids,SyncType syncType);
    void doUpdate(List<String> ids,Map<String,SyncLink> map,SyncType syncType);
}
