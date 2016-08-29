package com.tingo.dao.target;

import com.tingo.dto.target.HrmDepartment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 16/8/29.
 */
@Repository
public class TargetDeptDao extends TargetDao<HrmDepartment> {

    public Integer saveDept(List<HrmDepartment> depts) {
        return null;
    }

    public Integer saveDept(HrmDepartment dept) {
        return null;
    }

    public Integer update(HrmDepartment department) {
        return null;
    }

    public List<Long> queryIds() {
        return null;
    }
}
