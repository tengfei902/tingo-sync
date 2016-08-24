package com.tingo.sync;

import com.tingo.dto.SyncFieldDTO;
import com.tingo.dto.SyncTableDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/24.
 */
public interface DataSync {
    List<SyncTableDTO> getSyncTables();
    Map<Long,List<SyncFieldDTO>> getFieldMap();
    List queryFromOrigin(SyncTableDTO syncTable,List<SyncFieldDTO> syncFields);
    List queryFromLink(SyncTableDTO syncTable);
    void saveToTarget();
}
