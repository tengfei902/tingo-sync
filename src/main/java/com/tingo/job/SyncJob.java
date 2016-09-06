package com.tingo.job;

import com.tingo.sync.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by user on 16/8/29.
 */
@Component
public class SyncJob implements ISyncJob {
    @Autowired
    private SyncService syncService;

    @Scheduled(cron="0 0/5 * * * ?")
    public void doSync() {
        syncService.doSync();
    }
}
