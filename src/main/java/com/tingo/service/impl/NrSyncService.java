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
import org.apache.commons.lang3.time.DateFormatUtils;
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
        doc.setDocsubject(fwOaNr.getNrbt());
        doc.setDoctype(new BigDecimal(1));
        doc.setDoclangurage(new BigDecimal(7));
        doc.setDocreplyable("0");   //是否允许回复
        doc.setReplydocid(new BigDecimal(0)); //回复文件id
        doc.setItemmaincategoryid(new BigDecimal(0));
        doc.setItemmaincategoryid(new BigDecimal(0));
        doc.setHrmresid(new BigDecimal(0));
        doc.setCrmid(new BigDecimal(0));
        doc.setProjectid(new BigDecimal(0));
        doc.setFinanceid(new BigDecimal(0));
        String userId = LocalCache.getInstance().getTargetByOrigin(SyncType.hrm,fwOaNr.getSrry());
        if(!StringUtils.isEmpty(userId)) {
            doc.setDoccreaterid(new BigDecimal(userId)); //创建者id
            doc.setDoclastmoduserid(new BigDecimal(userId)); //最后修改者id
            doc.setOwnerid(new BigDecimal(userId));
        }
        doc.setDocdepartmentid(new BigDecimal(1)); //创建者所在部门
        doc.setDoccreatedate(DateFormatUtils.format(fwOaNr.getSrsj(),DateFormatUtils.ISO_DATE_FORMAT.getPattern()));
        doc.setDoccreatetime(DateFormatUtils.format(fwOaNr.getSrsj(),DateFormatUtils.ISO_TIME_FORMAT.getPattern()));
        doc.setDoclastmoddate(DateFormatUtils.format(fwOaNr.getSrsj(),DateFormatUtils.ISO_DATE_FORMAT.getPattern()));
        doc.setDoclastmodtime(DateFormatUtils.format(fwOaNr.getSrsj(),DateFormatUtils.ISO_TIME_FORMAT.getPattern()));
        String shId = LocalCache.getInstance().getTargetByOrigin(SyncType.hrm,fwOaNr.getShry());
        if(!StringUtils.isEmpty(shId)){
            doc.setDocapproveuserid(new BigDecimal(shId));
        }else{
            doc.setDocapproveuserid(new BigDecimal(0));
        }
        doc.setDocarchiveuserid(new BigDecimal(0));
        //doc.setParentids("0"); //文档父节点
        doc.setAssetid(new BigDecimal(0));
        doc.setAccessorycount(new BigDecimal(0)); //附件数量
        doc.setReplaydoccount(new BigDecimal(0)); //回复文档数量
        doc.setUsertype("1");
        doc.setCancopy("1");
        doc.setCanremind("0"); //回复是否提醒
        doc.setCountmark(new BigDecimal(0));
        doc.setSummark(new BigDecimal(0));
        doc.setDocextendname("html");
        doc.setInvalidationdate(fwOaNr.getGqrq().toString());
        doc.setIstop(new BigDecimal(0)); //置顶
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
