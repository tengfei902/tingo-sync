package com.tingo.sync;

import com.tingo.dto.SyncFieldDTO;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/24.
 */
public interface DataSync {
    List<SyncTableDTO> getSyncTables();
    List<SyncFieldDTO> getSyncFields();
    List<SyncLinkDTO> getSyncLinks(Long tableId);
    List<Long> getSyncIds(SyncTableDTO table);
    void doSave(List<Long> ids,SyncTableDTO table);
    void doUpdate(List<Long> ids,Map<Long,SyncLinkDTO> map,SyncTableDTO table);
}
