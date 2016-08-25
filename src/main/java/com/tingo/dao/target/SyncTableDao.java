package com.tingo.dao.target;

import com.tingo.dto.SyncTableDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tengfei on 16/8/26.
 */
@Repository
public class SyncTableDao extends TargetDao<SyncTableDTO> {

    private static final String GET_SYNC_TABLES_MAPPER = "SyncTableMapper.getSyncTables";

    public List<SyncTableDTO> getSyncTables() {
        return super.query(GET_SYNC_TABLES_MAPPER);
    }
}
