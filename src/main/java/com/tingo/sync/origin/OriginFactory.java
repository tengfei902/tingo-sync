package com.tingo.sync.origin;

import com.tingo.dao.origin.IOriginDao;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tengfei on 16/8/26.
 */
@Service
public class OriginFactory {
    private String orginTables;
    private static Map<String,IOriginDao> originDaoMap = new ConcurrentHashMap<>();


    public OriginFactory(String originTables) {
        this.orginTables = originTables;
    }

    public static IOriginDao getOriginDao(String tableName) {
        if(MapUtils.isEmpty(originDaoMap)) {

        }
        return originDaoMap.get(tableName);
    }
}
