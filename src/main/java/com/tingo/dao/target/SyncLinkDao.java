package com.tingo.dao.target;

import com.tingo.dto.SyncLinkDTO;
import com.tingo.enums.StatusType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tengfei on 16/8/25.
 */
@Repository
public class SyncLinkDao extends TargetDao<SyncLinkDTO> {
    private static final String GET_SYNC_LINKS_MAPPER = "SyncLinkMapper.getSyncLinkList";

    public List<SyncLinkDTO> getSyncLinkList(Long tableId) {
        Map<String,Object> params = new HashMap<>();
        params.put("tableId",tableId);
        params.put("status", StatusType.VALID.getValue());
        return super.query(GET_SYNC_LINKS_MAPPER,params);
    }

    public Integer save(SyncLinkDTO syncLink) {
        return super.insert("",syncLink);
    }
}
