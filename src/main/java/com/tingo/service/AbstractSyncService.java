package com.tingo.service;

import com.tingo.dao.target.SyncLinkDao;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.enums.StatusType;
import com.tingo.enums.SyncType;
import com.tingo.utils.ApplicationContextUtil;

/**
 * Created by user on 16/8/29.
 */
public abstract class AbstractSyncService implements ISyncService {

    private SyncLinkDao syncLinkDao = ApplicationContextUtil.getBean(SyncLinkDao.class);

    public abstract SyncType getSyncType();

    protected void saveSyncLink(Long originId,Long targetId) {
        SyncLinkDTO syncLink = new SyncLinkDTO();
        syncLink.setOriginId(originId);
        syncLink.setTargetId(targetId);
        syncLink.setSyncType(getSyncType());
        syncLink.setStatus(StatusType.VALID.getValue());

        syncLinkDao.save(syncLink);
    }
}
