package com.yf.kp.service.impl;

import com.yf.kp.service.AbstractService;
import com.yf.kp.utility.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

/**
 *
 * @author BlackCode
 * @param <T>
 */
public class AbstractServiceImpl<T> extends HibernateUtil implements AbstractService<T> {

    protected Class<T> model;

    public AbstractServiceImpl(Class<T> model) {
        this.model = model;
    }

    @Override
    public void save(T t) throws HibernateException {
        connect();
        try {
            manager().persist(t);
            commit();
        } catch (HibernateException ex) {
            rollback();
            throw ex;
        } finally {
            close();
        }
    }

    @Override
    public void update(T t) throws HibernateException {
        connect();
        try {
            manager().merge(t);
            commit();
        } catch (HibernateException ex) {
            rollback();
            throw ex;
        } finally {
            close();
        }
    }

    @Override
    public void delete(Object kode) throws HibernateException {
        connect();
        try {
            T t = (T) manager().get(model, (Serializable) kode);
            manager().delete(manager().merge(t));
            commit();
        } catch (HibernateException ex) {
            rollback();
            throw ex;
        } finally {
            close();
        }
    }

    @Override
    public T findOne(Object kode) throws HibernateException {
        T t = null;
        connect();
        try {
            t = (T) manager().get(model, (Serializable) kode);
            commit();
        } catch (HibernateException ex) {
            rollback();
            throw ex;
        } finally {
            close();
        }
        return t;
    }

    @Override
    public List<T> findAll() throws HibernateException {
        List<T> list = new ArrayList<>();
        connect();
        try {
            Criteria c = manager().createCriteria(model);
            list = c.list();
            commit();
        } catch (HibernateException ex) {
            rollback();
            throw ex;
        } finally {
            close();
        }
        return list;
    }

    @Override
    public List<T> findAll(int halaman, int banyakBaris) throws HibernateException {
        List<T> list = new ArrayList<>();
        connect();
        try {
            Criteria c = manager().createCriteria(model);
            list = c.setFirstResult(banyakBaris * (halaman - 1)).setMaxResults(banyakBaris).list();
            commit();
        } catch (HibernateException ex) {
            rollback();
            throw ex;
        } finally {
            close();
        }
        return list;
    }

}
