package com.tingo.sync;

import com.tingo.dto.SyncFieldDTO;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;
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
        List<SyncTableDTO> tables = dataSync.getSyncTables();

        for(SyncTableDTO syncTable:tables) {
            syncTableData(syncTable);
        }
    }

    private void syncTableData(SyncTableDTO syncTable) {
        List<Long> syncIds = dataSync.getSyncIds(syncTable);
        List<Long> newIds = new ArrayList<>();
        List<Long> existedIds = new ArrayList<>();

        List<SyncLinkDTO> links = dataSync.getSyncLinks(syncTable.getId());
        Map<Long,SyncLinkDTO> linkMap = new HashMap<>();
        for(SyncLinkDTO link:links) {
            linkMap.put(link.getOriginId(),link);
            if(syncIds.contains(link.getOriginId())) {
                existedIds.add(link.getOriginId());
                continue;
            }
            newIds.add(link.getOriginId());
        }

        dataSync.doSave(newIds,syncTable);
        dataSync.doUpdate(existedIds,linkMap,syncTable);
    }
}
