package com.tingo.utils;

import com.tingo.dao.target.SyncLinkDao;
import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 16/9/2.
 */
public class LocalCache {

    private static Map<String,String> cacheMap = new ConcurrentHashMap<String,String>();

    private static LocalCache localCache;

    private static SyncLinkDao syncLinkDao = ApplicationContextUtil.getBean(SyncLinkDao.class);

    private LocalCache() {
        init();
    }

    public static LocalCache getInstance() {
        if(null == localCache) {
            localCache = new LocalCache();
        }
        return localCache;
    }

    public String getTargetByOrigin(SyncType syncType,String id) {
        if(null == cacheMap.get(buildKey(syncType,id,Type.origin))) {
            lazyLoad(syncType,id,Type.origin);
        }
        return cacheMap.get(buildKey(syncType,id,Type.origin));
    }

    public String getOriginByTarget(SyncType syncType,String id) {
        if(null == cacheMap.get(buildKey(syncType,id,Type.target))) {
            lazyLoad(syncType,id,Type.target);
        }
        return cacheMap.get(buildKey(syncType,id,Type.target));
    }

    private String buildKey(SyncType syncType,String id,LocalCache.Type type) {
        return String.format("%s:%s:%s",syncType.name(),id,type);
    }

    private void lazyLoad(SyncType syncType,String id,LocalCache.Type type) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("syncType",syncType);
        if(Type.origin == type) {
            params.put("originId",id);
            SyncLink syncLink = syncLinkDao.lazeyLoadSyncLink(params);
            cacheMap.put(buildKey(syncType,id,type),syncLink.getTargetId().toString());
        }
        if(Type.target == type) {
            params.put("targetId",id);
            SyncLink syncLink = syncLinkDao.lazeyLoadSyncLink(params);
            cacheMap.put(buildKey(syncType,id,type),syncLink.getOriginId().toString());
        }
    }

    private synchronized void init() {

    }

    enum Type {
        origin,target;
    }
}
