package com.tingo.sync;

import com.tingo.dao.target.SyncLinkDao;
import com.tingo.dao.target.SyncTableDao;
import com.tingo.dto.SyncFieldDTO;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;
import com.tingo.sync.origin.OriginFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/24.
 */
@Service
public class DataSyncImpl implements DataSync {
    @Autowired
    private SyncTableDao syncTableDao;
    @Autowired
    private SyncLinkDao syncLinkDao;
    @Autowired
    private OriginFactory originFactory;

    @Override
    public List<SyncTableDTO> getSyncTables() {
        return syncTableDao.getSyncTables();
    }

    @Override
    public List<SyncFieldDTO> getSyncFields() {
        return null;
    }

    @Override
    public List<SyncLinkDTO> getSyncLinks(Long tableId) {
        return syncLinkDao.getSyncLinkList(tableId);
    }

    @Override
    public List<Long> getSyncIds(SyncTableDTO table) {
        return originFactory.getOriginDao(table.getOriginTable()).queryIds();
    }

    @Override
    public void doSave(List<Long> ids, SyncTableDTO table) {
        
    }

    @Override
    public void doUpdate(List<Long> ids, Map<Long, SyncLinkDTO> map, SyncTableDTO table) {

    }
}
