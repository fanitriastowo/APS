package com.yf.kp.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author BlackCode
 */
public abstract class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private Session sess;
    private Transaction tx;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected Session manager() {
        return this.sess;
    }

    protected void connect() {
        sess = getSessionFactory().getCurrentSession();
        tx = sess.getTransaction();
        tx.begin();
    }

    protected void commit() {
        tx = manager().getTransaction();
        tx.commit();
    }

    protected void rollback() {
        tx = manager().getTransaction();
        if (tx != null) {
            tx.rollback();
        }
    }

    protected void close() {
        if (manager() != null && manager().isOpen()) {
            manager().close();
        }
    }
}
