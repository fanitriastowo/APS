package com.yf.kp.service;

import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author BlackCode
 * @param <T>
 */
public interface AbstractService<T> {

    public void save(T t) throws HibernateException;

    public void update(T t) throws HibernateException;

    public void delete(Object kode) throws HibernateException;

    public T findOne(Object kode) throws HibernateException;

    public List<T> findAll() throws HibernateException;

    public List<T> findAll(int halaman, int banyakBaris) throws HibernateException;
    
}
