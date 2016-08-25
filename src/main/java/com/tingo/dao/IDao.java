package com.tingo.dao;
import java.util.List;

/**
 * Created by tengfei on 2016/7/22.
 */
public interface IDao<T> {
    Integer insert(String statement, Object parameter);
    Integer batchInsert(String statement, Object parameter);
    Integer update(String statement, Object parameter);
    T queryForObject(String statement, Object parameter);
    List<T> query(String statement, Object parameter);
}
