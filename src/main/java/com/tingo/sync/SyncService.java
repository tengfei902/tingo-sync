package com.tingo.sync;

import com.tingo.dto.SyncFieldDTO;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Map<Long,List<SyncFieldDTO>> fieldMap = dataSync.getFieldMap();

        for(SyncTableDTO syncTable:tables) {
            List<SyncFieldDTO> fields = fieldMap.get(syncTable.getId());
            List originData = dataSync.queryFromOrigin(syncTable,fields);
            List synclinks = dataSync.queryFromLink(syncTable);
            saveToTarget(syncTable,originData,synclinks,fields);
        }
    }

    private void saveToTarget(SyncTableDTO syncTable,List originData,List syncLinks,List<SyncFieldDTO> fields ) {

    }
}
