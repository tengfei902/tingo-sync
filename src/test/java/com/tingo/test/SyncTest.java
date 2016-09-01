package com.tingo.test;

import com.tingo.dao.target.HrmResourceDao;
import com.tingo.enums.SyncType;
import com.tingo.sync.SyncService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tengfei on 16/9/1.
 */
public class SyncTest extends BaseAppTest {
    @Autowired
    private SyncService syncService;
    @Autowired
    private HrmResourceDao hrmResourceDao;

    @Test
    public void testSyncDebt() {
        syncService.syncTableData(SyncType.dept);
    }

    @Test
    public void testSyncHrm() {
        syncService.syncTableData(SyncType.hrm);
    }

    @Test
    public void testGetHrmId() {
        long id = hrmResourceDao.getHrmId();

        System.out.println("---------:"+id);

        Integer count = hrmResourceDao.updateHrmId(id);

        Assert.assertTrue(count==1);
    }
}
