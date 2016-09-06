package com.tingo.service.impl;

import com.google.common.collect.Lists;
import com.tingo.dao.origin.FwRyxxDao;
import com.tingo.dao.target.HrmResourceDao;
import com.tingo.dto.origin.FwRyxx;
import com.tingo.dto.target.HrmResource;
import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
import com.tingo.service.AbstractSyncService;
import com.tingo.utils.Converter;
import com.tingo.utils.LocalCache;
import com.tingo.utils.PinyinUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/29.
 */
@Service
public class HrmSyncService extends AbstractSyncService {
    @Autowired
    private FwRyxxDao originHrmDao;
    @Autowired
    private HrmResourceDao hrmResourceDao;

    @Override
    public SyncType getSyncType() {
        return SyncType.hrm;
    }

    @Override
    @Transactional
    public void save(List<String> ids) {

        if(CollectionUtils.isEmpty(ids)) {
            return;
        }

        List<List<String>> idlist = Lists.partition(ids,100);
        for(List<String> subIdList:idlist) {
            List<FwRyxx> originHrms = originHrmDao.selectByIds(subIdList);
            for(FwRyxx fwRyxx:originHrms) {
                HrmResource hrmResource = buildHrmResource(fwRyxx);
                hrmResourceDao.insert(hrmResource);
                hrmResource = hrmResourceDao.selectByFid(fwRyxx.getZgid());
                super.saveSyncLink(Long.parseLong(fwRyxx.getZgid()),hrmResource.getId());
            }
        }
    }

    private HrmResource buildHrmResource(FwRyxx fwRyxx) {
        HrmResource hrmResource = new HrmResource();
        hrmResource.setLoginid(fwRyxx.getZggh());
        if(!StringUtils.isEmpty(fwRyxx.getDlkl())) {
            hrmResource.setPassword(fwRyxx.getDlkl().toUpperCase());
        }
        hrmResource.setLastname(fwRyxx.getXm());
        hrmResource.setSex(Converter.convertSex(fwRyxx.getXb()));
        hrmResource.setSystemlanguage(new BigDecimal(7));
        hrmResource.setMaritalstatus("0");
        hrmResource.setLocationid(new BigDecimal(21));
        //hrmResource.setSeclevel();
        hrmResource.setSubcompanyid1(new BigDecimal(1));
        if(!StringUtils.isEmpty(fwRyxx.getSzks())) {
            String deptId = LocalCache.getInstance().getTargetByOrigin(SyncType.dept,fwRyxx.getSzks());
            if(!StringUtils.isEmpty(deptId)) {
                hrmResource.setDepartmentid(new BigDecimal(deptId));
            }
        }
//        hrmResource.setManagerid();
        hrmResource.setCreaterid(new BigDecimal(1));
        hrmResource.setCreatedate(DateFormatUtils.format(new Date(),DateFormatUtils.ISO_DATE_FORMAT.getPattern()));
        hrmResource.setLastmodid(new BigDecimal(1));
        hrmResource.setLastmoddate(DateFormatUtils.format(new Date(),DateFormatUtils.ISO_DATE_FORMAT.getPattern()));
        hrmResource.setHealthinfo("0");
        hrmResource.setJobcall(new BigDecimal(21));
//        hrmResource.setManagerstr();
        hrmResource.setStatus(new BigDecimal(1));
        hrmResource.setIslabouunion("1");
        hrmResource.setPinyinlastname(PinyinUtils.getFirstSpell(fwRyxx.getXm()));
        hrmResource.setFid(fwRyxx.getZgid());

        hrmResource.setId(getHrmId());

        return hrmResource;
    }

    private Long getHrmId() {
        Long id = hrmResourceDao.getHrmId();
        hrmResourceDao.updateHrmId(id);
        return id;
    }

    @Override
    @Transactional
    public void update(List<String> ids, Map<String, SyncLink> map) {
        if(CollectionUtils.isEmpty(ids)) {
            return;
        }

        List<List<String>> idList = Lists.partition(ids,100);
        for(List<String> subList:idList) {
            List<FwRyxx> originHrms = originHrmDao.selectByIds(subList);
            for(FwRyxx originHrm:originHrms) {
                HrmResource hrmResource = buildHrmResource(originHrm);
                hrmResourceDao.updateByPrimaryKey(hrmResource);
            }
        }
    }

    @Override
    public List<String> queryIds() {
        return originHrmDao.selectIds();
    }
}
