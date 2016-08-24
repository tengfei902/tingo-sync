package com.tingo.sync;

import com.tingo.dto.SyncFieldDTO;
import com.tingo.dto.SyncTableDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/24.
 */
@Service
public class DataSyncImpl implements DataSync {
    @Override
    public List<SyncTableDTO> getSyncTables() {
        return null;
    }

    @Override
    public Map<Long, List<SyncFieldDTO>> getFieldMap() {
        return null;
    }

    @Override
    public List queryFromOrigin(SyncTableDTO syncTable, List<SyncFieldDTO> syncFields) {
        return null;
    }

    @Override
    public List queryFromLink(SyncTableDTO syncTable) {
        return null;
    }

    @Override
    public void saveToTarget() {

    }
}
