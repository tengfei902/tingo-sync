package com.tingo.sync;

import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/24.
 */
@Service
public class SyncService {
    @Autowired
    private DataSync dataSync;

    public void doSync() {
        for(SyncType syncType:SyncType.values()) {
            syncTableData(syncType);
        }
    }

    public void syncTableData(SyncType syncType) {
        List<String> syncIds = dataSync.getSyncIds(syncType);
        List<String> newIds = new ArrayList<>();
        List<String> existedIds = new ArrayList<>();

        List<SyncLink> links = dataSync.getSyncLinks(syncType);
        Map<String,SyncLink> linkMap = new HashMap<>();

        for(SyncLink link:links) {
            linkMap.put(link.getOriginId().toString(),link);
        }

        for(String syncId:syncIds) {
            if(linkMap.containsKey(syncId)) {
                existedIds.add(syncId);
                continue;
            }
            newIds.add(syncId);
        }

        dataSync.doSave(newIds,syncType);
        dataSync.doUpdate(existedIds,linkMap,syncType);
    }
}
