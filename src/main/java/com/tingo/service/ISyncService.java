package com.tingo.service;

import com.tingo.dto.SyncLinkDTO;
import com.tingo.dto.SyncTableDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by tengfei on 16/8/29.
 */
public interface ISyncService {
    void save(List<Long> ids, SyncTableDTO table);
    void update(List<Long> ids, Map<Long, SyncLinkDTO> map, SyncTableDTO table);
    List<Long> queryIds();
}
