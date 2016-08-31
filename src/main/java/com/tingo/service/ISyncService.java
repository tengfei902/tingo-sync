package com.tingo.service;

import com.tingo.dto.target.SyncLink;

import java.util.List;
import java.util.Map;

/**
 * Created by tengfei on 16/8/29.
 */
public interface ISyncService {
    void save(List<String> ids);
    void update(List<String> ids, Map<String, SyncLink> map);
    List<String> queryIds();
}
