package com.tingo.test;

import com.tingo.enums.SyncType;
import com.tingo.sync.SyncService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tengfei on 16/9/1.
 */
public class SyncTest extends BaseAppTest {
    @Autowired
    private SyncService syncService;

    @Test
    public void testSyncDebt() {
        syncService.syncTableData(SyncType.dept);
    }
}
