package com.tingo.service;

import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/29.
 */
@Service
public class HrmSyncService implements ISyncService {
    @Override
    public void save(List<Long> ids, SyncTableDTO table) {

    }

    @Override
    public void update(List<Long> ids, Map<Long, SyncLinkDTO> map, SyncTableDTO table) {

    }

    @Override
    public List<Long> queryIds() {
        return null;
    }
}
