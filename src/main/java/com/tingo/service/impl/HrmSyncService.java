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
import com.tingo.utils.PinyinUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void save(List<String> ids) {

        if(CollectionUtils.isEmpty(ids)) {
            return;
        }
        List<FwRyxx> originHrms = selectByIds(ids);
        for(FwRyxx fwRyxx:originHrms) {
            HrmResource hrmResource = buildHrmResource(fwRyxx);
            hrmResourceDao.insert(hrmResource);
            hrmResource = hrmResourceDao.selectByFid(fwRyxx.getZgid());
            super.saveSyncLink(Long.parseLong(fwRyxx.getZgid()),hrmResource.getId());
        }
    }

    private List<FwRyxx> selectByIds(List<String> ids) {
        List<FwRyxx> list = new ArrayList<>();
        List<List<String>> subIds = Lists.partition(ids,100);

        for(List<String> preids:subIds) {
            List<FwRyxx> originHrms = originHrmDao.selectByIds(preids);
            if(CollectionUtils.isNotEmpty(originHrms)) {
                list.addAll(originHrms);
            }
        }

        return list;
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
            hrmResource.setDepartmentid(new BigDecimal(fwRyxx.getSzks()));
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

        return hrmResource;
    }

    @Override
    public void update(List<String> ids, Map<String, SyncLink> map) {

        List<FwRyxx> originHrms = originHrmDao.selectByIds(ids);
        for(FwRyxx originHrm:originHrms) {
            HrmResource hrmResource = buildHrmResource(originHrm);
            hrmResourceDao.updateByPrimaryKey(hrmResource);
        }
    }

    @Override
    public List<String> queryIds() {
        return originHrmDao.selectIds();
    }
}
