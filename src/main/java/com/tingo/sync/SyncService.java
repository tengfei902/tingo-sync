package com.tingo.sync;

import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
import com.tingo.service.SyncServiceFactory;
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
    @Autowired
    private SyncServiceFactory syncServiceFactory;

    public void doSync() {
        for(SyncType syncType:SyncType.values()) {
            if(null == syncServiceFactory.getSyncService(syncType)) {
                continue;
            }
            syncTableData(syncType);
        }
    }

    public void syncTableData(SyncType syncType) {
        List<String> syncIds = dataSync.getSyncIds(syncType);
        List<String> newIds = new ArrayList<String>();
        List<String> existedIds = new ArrayList<String>();

        List<SyncLink> links = dataSync.getSyncLinks(syncType);
        Map<String,SyncLink> linkMap = new HashMap<String,SyncLink>();

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
