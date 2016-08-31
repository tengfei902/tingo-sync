package com.tingo.service.impl;

import com.tingo.dao.origin.OriginDebtDao;
import com.tingo.dao.target.HrmDepartmentDao;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;
import com.tingo.dto.origin.OriginDebtDTO;
import com.tingo.dto.target.HrmDepartment;
import com.tingo.enums.SyncType;
import com.tingo.service.AbstractSyncService;
import com.tingo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/29.
 */
public class DebtSyncService extends AbstractSyncService {
    @Autowired
    private OriginDebtDao originDebtDao;
    @Autowired
    private HrmDepartmentDao hrmDepartmentDao;

    @Override
    public SyncType getSyncType() {
        return SyncType.dept;
    }

    @Override
    public void save(List<Long> ids, SyncTableDTO table) {
        List<OriginDebtDTO> originDebtList = originDebtDao.queryByIds(ids);
        for(OriginDebtDTO oridept:originDebtList) {
            HrmDepartment hrmDepartment = buildHrmDepartment(oridept);
            hrmDepartmentDao.insert(hrmDepartment);

            super.saveSyncLink(oridept.getId(),hrmDepartment.getId());
        }
    }

    private HrmDepartment buildHrmDepartment(OriginDebtDTO oridept) {
        HrmDepartment dept = new HrmDepartment();
        dept.setDepartmentcode(oridept.getBmdm());
        dept.setDepartmentname(oridept.getBmmc());
        dept.setDepartmentmark(oridept.getBmmc());
        dept.setAllsupdepid(oridept.getSjbm());
        dept.setSupdepid(Long.parseLong(oridept.getSjbm()));
        dept.setSubcompanyid1(Constants.Properties.SUB_COMPANY_ID);
        dept.setShoworder(oridept.getId());
        return dept;
    }



    @Override
    public void update(List<Long> ids, Map<Long, SyncLinkDTO> map, SyncTableDTO table) {
        List<OriginDebtDTO> originDebtList = originDebtDao.queryByIds(ids);
        for(OriginDebtDTO originDebt:originDebtList) {
            HrmDepartment department = buildHrmDepartment(originDebt);
            hrmDepartmentDao.updateByPrimaryKey(department);
        }
    }

    @Override
    public List<Long> queryIds() {
        return hrmDepartmentDao.queryIds();
    }
}
