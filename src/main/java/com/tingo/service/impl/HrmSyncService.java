package com.tingo.service.impl;

import com.tingo.dao.origin.OriginHrmDao;
import com.tingo.dao.target.HrmResourceDao;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;
import com.tingo.dto.origin.OriginHrmDTO;
import com.tingo.dto.target.HrmResource;
import com.tingo.enums.SyncType;
import com.tingo.service.AbstractSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/29.
 */
@Service
public class HrmSyncService extends AbstractSyncService {
    @Autowired
    private OriginHrmDao originHrmDao;
    @Autowired
    private HrmResourceDao hrmResourceDao;

    @Override
    public SyncType getSyncType() {
        return SyncType.hrm;
    }

    @Override
    public void save(List<Long> ids, SyncTableDTO table) {
        List<OriginHrmDTO> originHrms = originHrmDao.queryByIds(ids);
        for(OriginHrmDTO originHrm:originHrms) {
            HrmResource hrmResource = buildHrmResource(originHrm);
            hrmResourceDao.insert(hrmResource);

            super.saveSyncLink(originHrm.getId(),hrmResource.getId());
        }
    }

    private HrmResource buildHrmResource(OriginHrmDTO originHrm) {
        HrmResource hrmResource = new HrmResource();
        
        return hrmResource;
    }

    @Override
    public void update(List<Long> ids, Map<Long, SyncLinkDTO> map, SyncTableDTO table) {
        List<OriginHrmDTO> originHrms = originHrmDao.queryByIds(ids);
        for(OriginHrmDTO originHrm:originHrms) {
            HrmResource hrmResource = buildHrmResource(originHrm);
            hrmResourceDao.updateByPrimaryKey(hrmResource);
        }
    }

    @Override
    public List<Long> queryIds() {
        return originHrmDao.queryIds();
    }
}
