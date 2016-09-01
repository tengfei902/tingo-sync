package com.tingo.service.impl;

import com.tingo.dao.origin.FwBmxxDao;
import com.tingo.dao.target.HrmDepartmentDao;
import com.tingo.dto.origin.FwBmxx;
import com.tingo.dto.target.HrmDepartment;
import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
import com.tingo.service.AbstractSyncService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/29.
 */
@Service
public class DebtSyncService extends AbstractSyncService {
    @Autowired
    private FwBmxxDao originDebtDao;
    @Autowired
    private HrmDepartmentDao hrmDepartmentDao;

    @Override
    public SyncType getSyncType() {
        return SyncType.dept;
    }

    @Transactional
    @Override
    public void save(List<String> ids) {

        if(CollectionUtils.isEmpty(ids)) {
            return;
        }
        List<FwBmxx> fwBmxxes = originDebtDao.selectByIds(ids);

        for(FwBmxx fwBmxx:fwBmxxes) {
            HrmDepartment hrmDepartment = buildHrmDepartment(fwBmxx);
            hrmDepartmentDao.insert(hrmDepartment);
            hrmDepartment = hrmDepartmentDao.selectByFid(fwBmxx.getKsid());
            super.saveSyncLink(Long.parseLong(hrmDepartment.getFid()),hrmDepartment.getId());
        }
        updateSupDebtId();
    }

    private void updateSupDebtId() {
        hrmDepartmentDao.updateSupDebtId();
    }

    private HrmDepartment buildHrmDepartment(FwBmxx fwBmxx) {
        HrmDepartment dept = new HrmDepartment();
        dept.setDepartmentmark(fwBmxx.getKsmc());
        dept.setDepartmentname(fwBmxx.getKsmc());
        dept.setSubcompanyid1(1L);
        if(null != fwBmxx.getSjid()) {
            if("*".equals(fwBmxx.getSjid())) {
                dept.setSupdepid(0L);
            } else {
                dept.setSupdepid(Long.parseLong(fwBmxx.getSjid()));
            }
        }
        dept.setDepartmentcode(fwBmxx.getKsdm());
        if(null != fwBmxx.getKsdm()) {
            dept.setShoworder(Long.parseLong(fwBmxx.getKsdm()));
        }
        dept.setFid(fwBmxx.getKsid());
        return dept;
    }



    @Override
    public void update(List<String> ids, Map<String, SyncLink> map) {
        List<FwBmxx> originDebtList = originDebtDao.selectByIds(ids);
        if(CollectionUtils.isEmpty(originDebtList)) {
            return;
        }
        for(FwBmxx originDebt:originDebtList) {
            HrmDepartment department = buildHrmDepartment(originDebt);
            department.setId(map.get(originDebt.getKsid()).getTargetId());
            hrmDepartmentDao.updateByPrimaryKey(department);
        }
    }

    @Override
    public List<String> queryIds() {
        return originDebtDao.selectIds();
    }
}
