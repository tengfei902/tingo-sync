package com.tingo.dao.target;

import com.tingo.dao.AbstractBaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by tengfei on 16/8/25.
 */
@Repository
public class TargetDao<T> extends AbstractBaseDao<T> {
    @Autowired
    @Qualifier("targetSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    @Override
    protected SqlSessionFactory getSessionFactory() {
        return null;
    }
}
