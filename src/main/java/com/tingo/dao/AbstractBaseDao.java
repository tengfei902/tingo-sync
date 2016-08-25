package com.tingo.dao;

import com.tingo.exception.TingoException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tengfei on 2016/7/22.
 */
@Repository
public abstract class AbstractBaseDao<T> implements IDao<T> {

    protected abstract SqlSessionFactory getSessionFactory();

    public Integer insert(String statement, Object parameter) {
        SqlSession session = getSessionFactory().openSession();
        try {
            return session.insert(statement,parameter);
        } catch (Exception e) {
            throw new TingoException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public Integer batchInsert(String statement,Object parameter) {
        SqlSession session = getSessionFactory().openSession();
        return 0;
    }

    public Integer update(String statement, Object parameter) {
        SqlSession session = getSessionFactory().openSession();
        try {
            return session.update(statement,parameter);
        } catch (Exception e) {
            throw new TingoException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public T queryForObject(String statement, Object parameter) {
        SqlSession session = getSessionFactory().openSession();
        try {
            return session.selectOne(statement,parameter);
        } catch (Exception e) {
            throw new TingoException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<T> query(String statement, Object parameter) {
        SqlSession session = getSessionFactory().openSession();
        try {
            return session.selectList(statement,parameter);
        } catch (Exception e) {
            throw new TingoException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<T> query(String statement) {
        SqlSession session = getSessionFactory().openSession();
        try {
            return session.selectList(statement);
        } catch (Exception e) {
            throw new TingoException(e.getMessage());
        } finally {
            session.close();
        }
    }
}
