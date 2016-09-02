package com.tingo.service.impl;

import com.tingo.dao.origin.FwOaNrDao;
import com.tingo.dao.target.DocDetailContentDao;
import com.tingo.dao.target.DocDetailDao;
import com.tingo.dto.origin.FwOaNr;
import com.tingo.dto.target.DocDetail;
import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
import com.tingo.service.AbstractSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/9/1.
 */
@Service
public class NrSyncService extends AbstractSyncService {
    @Autowired
    private FwOaNrDao originNrDao;

    @Autowired
    private DocDetailDao docDetailDao;

    @Autowired
    private DocDetailContentDao docDetailContentDao;

    @Override
    public SyncType getSyncType() {
        return SyncType.nr;
    }

    @Override
    public void save(List<String> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return;
        }

        List<FwOaNr> originNrs = originNrDao.selectByIds(ids);

        for(FwOaNr fwOaNr:originNrs){
            DocDetail detail = bulidDocDetail(fwOaNr);
        }
    }

    private DocDetail bulidDocDetail(FwOaNr fwOaNr){
        DocDetail doc = new DocDetail();
        doc.
    }

    @Override
    public void update(List<String> ids, Map<String, SyncLink> map) {

    }

    @Override
    public List<String> queryIds() {
        return null;
    }
}
