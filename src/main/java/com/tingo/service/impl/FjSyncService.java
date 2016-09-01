package com.tingo.service.impl;

import com.tingo.dto.target.SyncLink;
import com.tingo.enums.SyncType;
import com.tingo.service.AbstractSyncService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/9/1.
 */
@Service
public class FjSyncService extends AbstractSyncService {

    @Override
    public SyncType getSyncType() {
        return null;
    }

    @Override
    public void save(List<String> ids) {

    }

    @Override
    public void update(List<String> ids, Map<String, SyncLink> map) {

    }

    @Override
    public List<String> queryIds() {
        return null;
    }
}
