package com.tingo.service;

import com.tingo.dao.origin.OriginDebtDao;
import com.tingo.dao.target.TargetDeptDao;
import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;
import com.tingo.dto.origin.OriginDebtDTO;
import com.tingo.dto.target.HrmDepartment;
import com.tingo.enums.SyncType;
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
    private TargetDeptDao targetDeptDao;

    @Override
    public SyncType getSyncType() {
        return SyncType.dept;
    }

    @Override
    public void save(List<Long> ids, SyncTableDTO table) {
        List<OriginDebtDTO> originDebtList = originDebtDao.queryByIds(ids);
        for(OriginDebtDTO oridept:originDebtList) {
            HrmDepartment hrmDepartment = buildHrmDepartment(oridept);
            targetDeptDao.saveDept(hrmDepartment);

            super.saveSyncLink(oridept.getId(),hrmDepartment.getId());
        }
    }

    private HrmDepartment buildHrmDepartment(OriginDebtDTO oridept) {
        HrmDepartment dept = new HrmDepartment();
        dept.setDepartmentCode(oridept.getBmdm());
        dept.setDepartmentName(oridept.getBmmc());
        dept.setDepartmentMark(oridept.getBmmc());
        dept.setAllSupDeptId(oridept.getSjbm());
        dept.setSupDeptId(Long.parseLong(oridept.getSjbm()));
        dept.setSubCompanyId1();
        dept.setShowOrder();
        return dept;
    }



    @Override
    public void update(List<Long> ids, Map<Long, SyncLinkDTO> map, SyncTableDTO table) {
        List<OriginDebtDTO> originDebtList = originDebtDao.queryByIds(ids);
        for(OriginDebtDTO originDebt:originDebtList) {
            HrmDepartment department = buildHrmDepartment(originDebt);
            targetDeptDao.update(department);
        }
    }

    @Override
    public List<Long> queryIds() {
        return targetDeptDao.queryIds();
    }
}
