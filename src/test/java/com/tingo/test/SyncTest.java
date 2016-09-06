package com.tingo.test;

import com.tingo.dao.target.HrmResourceDao;
import com.tingo.dao.target.SyncLinkDao;
import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
import com.tingo.sync.SyncService;
import com.tingo.utils.LocalCache;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by tengfei on 16/9/1.
 */
public class SyncTest extends BaseAppTest {
    @Autowired
    private SyncService syncService;
    @Autowired
    private HrmResourceDao hrmResourceDao;
    @Autowired
    private SyncLinkDao syncLinkDao;

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

    @Test
    public void testLocalCache() {
        List<SyncLink> deptLins = syncLinkDao.selectSyncLinks(SyncType.dept.name());
        for(SyncLink syncLink:deptLins) {
            String id = LocalCache.getInstance().getTargetByOrigin(SyncType.dept,syncLink.getOriginId().toString());
            Assert.assertEquals(id,syncLink.getTargetId().toString());
        }

        List<SyncLink> hrmLinks = syncLinkDao.selectSyncLinks(SyncType.hrm.name());
        for(SyncLink syncLink:hrmLinks) {
            String id = LocalCache.getInstance().getTargetByOrigin(SyncType.hrm,syncLink.getOriginId().toString());
            Assert.assertEquals(id,syncLink.getTargetId().toString());
        }

        List<SyncLink> categoryLins = syncLinkDao.selectSyncLinks(SyncType.category.name());
        for(SyncLink syncLink:categoryLins) {
            String id = LocalCache.getInstance().getTargetByOrigin(SyncType.category,syncLink.getOriginId().toString());
            Assert.assertEquals(id,syncLink.getTargetId().toString());
        }
    }

    @Test
    public void testSyncDoc() {
        syncService.syncTableData(SyncType.nr);
    }

    @Test
    public void testSyncFj(){
        syncService.syncTableData(SyncType.fj);
    }
}
