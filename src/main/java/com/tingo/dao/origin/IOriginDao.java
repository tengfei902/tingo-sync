package com.tingo.dao.origin;

import java.util.List;

/**
 * Created by tengfei on 16/8/26.
 */
public interface IOriginDao<T> {
    List<Long> queryIds();
    List<T> queryForList();
}
