package com.tingo.sync;

import com.tingo.dao.target.SyncLinkDao;
import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
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
    private SyncLinkDao syncLinkDao;
    @Autowired
    private SyncServiceFactory syncServiceFactory;
    @Override
    public List<SyncLink> getSyncLinks(SyncType syncType) {
       return syncLinkDao.selectSyncLinks(syncType.name());
    }

    @Override
    public List<String> getSyncIds(SyncType syncType) {
        return syncServiceFactory.getSyncService(syncType).queryIds();
    }

    @Override
    public void doSave(List<String> ids, SyncType syncType) {
        syncServiceFactory.getSyncService(syncType).save(ids);
    }

    @Override
    public void doUpdate(List<String> ids, Map<String, SyncLink> map, SyncType syncType) {
        syncServiceFactory.getSyncService(syncType).update(ids,map);
    }
}
