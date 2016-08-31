package com.tingo.sync;

import com.tingo.dao.target.SyncLinkDao;
import com.tingo.dao.target.SyncTableDao;
import com.tingo.dto.SyncFieldDTO;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;
import com.tingo.service.SyncServiceFactory;
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
    private SyncServiceFactory syncServiceFactory;

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
//        return syncLinkDao.getSyncLinkList(tableId);
        return null;
    }

    @Override
    public List<Long> getSyncIds(SyncTableDTO table) {
        return syncServiceFactory.getSyncService(table.getSyncType()).queryIds();
    }

    @Override
    public void doSave(List<Long> ids, SyncTableDTO table) {
        syncServiceFactory.getSyncService(table.getSyncType()).save(ids,table);
    }

    @Override
    public void doUpdate(List<Long> ids, Map<Long, SyncLinkDTO> map, SyncTableDTO table) {
        syncServiceFactory.getSyncService(table.getSyncType()).update(ids,map,table);
    }
}
