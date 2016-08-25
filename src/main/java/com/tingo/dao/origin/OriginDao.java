package com.tingo.dao.origin;

import com.tingo.dao.AbstractBaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by tengfei on 16/8/26.
 */
public class OriginDao<T> extends AbstractBaseDao<T> {
    @Autowired
    @Qualifier("originSqlSessionFactory")
    private SqlSessionFactory sessionFactory;

    @Override
    protected SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
