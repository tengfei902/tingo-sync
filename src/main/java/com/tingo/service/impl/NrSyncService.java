package com.tingo.service.impl;

import com.google.common.collect.Lists;
import com.tingo.dao.origin.FwOaNrDao;
import com.tingo.dao.target.DocDetailContentDao;
import com.tingo.dao.target.DocDetailDao;
import com.tingo.dto.origin.FwOaNr;
import com.tingo.dto.target.DocDetail;
import com.tingo.dto.target.DocDetailContent;
import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
import com.tingo.service.AbstractSyncService;
import com.tingo.utils.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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

    @Transactional
    @Override
    public void save(List<String> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return;
        }

        List<List<String>> idList = Lists.partition(ids,100);
        for(List<String> subIdList:idList) {

            List<FwOaNr> originNrs = originNrDao.selectByIds(subIdList);
            for(FwOaNr fwOaNr:originNrs){
                DocDetail detail = bulidDocDetail(fwOaNr);
                docDetailDao.insert(detail);
                detail = docDetailDao.selectByYYId(fwOaNr.getNrid());
                super.saveSyncLink(Long.parseLong(fwOaNr.getNrid()), detail.getId().longValue());
                try {
                    DocDetailContent docDetailContent = buildDocDetailContent(fwOaNr,detail.getId());
                    docDetailContentDao.insert(docDetailContent);
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }
        }
    }

    private DocDetail bulidDocDetail(FwOaNr fwOaNr){
        DocDetail doc = new DocDetail();
        doc.setYydocid(fwOaNr.getNrid());
        String secCategory = LocalCache.getInstance().getTargetByOrigin(SyncType.category,fwOaNr.getLmid());
        if(!StringUtils.isEmpty(secCategory)) {
            doc.setSeccategory(new BigDecimal(secCategory));
        }
        //todo 添加set代码

        return doc;
    }

    private DocDetailContent buildDocDetailContent(FwOaNr fwOaNr, BigDecimal docId) throws UnsupportedEncodingException {
        DocDetailContent docDetailContent = new DocDetailContent();
        docDetailContent.setDoccontent(new String(fwOaNr.getNr(),"UTF-8"));
        docDetailContent.setDocid(docId);
        return docDetailContent;
    }

    @Override
    public void update(List<String> ids, Map<String, SyncLink> map) {

    }

    @Override
    public List<String> queryIds() {
        return originNrDao.selectIds();
    }
}
