package com.tingo.service;

import com.tingo.enums.SyncType;
import com.tingo.service.impl.*;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 16/8/29.
 */
@Service
public class SyncServiceFactory {

    Map<SyncType,ISyncService> syncServiceMap = new ConcurrentHashMap<>();
    @Autowired
    private DebtSyncService debtSyncService;
    @Autowired
    private HrmSyncService hrmSyncService;
    @Autowired
    private LmSyncService lmSyncService;
    @Autowired
    private NrSyncService nrSyncService;
    @Autowired
    private FjSyncService fjSyncService;

    public ISyncService getSyncService(SyncType syncType) {
        if(MapUtils.isEmpty(syncServiceMap)) {
            initSyncMap();
        }
        return syncServiceMap.get(syncType);
    }

    private void initSyncMap() {
        syncServiceMap.put(SyncType.dept,debtSyncService);
        syncServiceMap.put(SyncType.hrm,hrmSyncService);
        syncServiceMap.put(SyncType.lm,lmSyncService);
        syncServiceMap.put(SyncType.nr,nrSyncService);
        syncServiceMap.put(SyncType.fj,fjSyncService);
    }
}
