package com.yf.kp.service.impl;

import com.yf.kp.service.CountService;
import com.yf.kp.utility.HibernateUtil;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author anonymous
 */
public class CountServiceImpl extends HibernateUtil implements CountService {

    @Override
    public Long countKelas() {
        Object jml = 0;
        connect();
        try {
            Query q = manager().createQuery("SELECT COUNT(K.id) AS jml FROM Kelas K");
            jml = q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            rollback();
        } finally {
            close();
        }
        return (Long) jml;
    }

    @Override
    public Long countAngsuran() {
        Object jml = 0;
        connect();
        try {
            Query q = manager().createQuery("SELECT COUNT(A.id) AS jml FROM Angsuran A");
            jml = q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            rollback();
        } finally {
            close();
        }
        return (Long) jml;
    }

    @Override
    public Long countBulanan() {
        Object jml = 0;
        connect();
        try {
            Query q = manager().createQuery("SELECT COUNT(B.id) AS jml FROM Bulanan B");
            jml = q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            rollback();
        } finally {
            close();
        }
        return (Long) jml;
    }

    @Override
    public Long countTunai() {
        Object jml = 0;
        connect();
        try {
            Query q = manager().createQuery("SELECT COUNT(T.id) AS jml FROM Tunai T");
            jml = q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            rollback();
        } finally {
            close();
        }
        return (Long) jml;
    }

    @Override
    public Long countSiswa() {
        Object jml = 0;
        connect();
        try {
            Query q = manager().createQuery("SELECT COUNT(S.id) AS jml FROM Siswa S");
            jml = q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            rollback();
        } finally {
            close();
        }
        return (Long) jml;
    }

}
