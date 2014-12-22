package com.yf.kp.service.impl;

import com.yf.kp.model.Siswa;
import com.yf.kp.service.SiswaService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author anonymous
 */
public class SiswaServiceImpl extends AbstractServiceImpl<Siswa> implements SiswaService {

    public SiswaServiceImpl() {
        super(Siswa.class);
    }

    @Override
    public List<Siswa> findByNis(String nis) {
        List<Siswa> listSiswa = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT s FROM Siswa s WHERE s.nis LIKE :kode");
            q.setParameter("kode", "%" + nis + "%");
            listSiswa = q.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return listSiswa;
    }

    @Override
    public List<Siswa> findByNama(String nama) {
        List<Siswa> listSiswa = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT s FROM Siswa s WHERE s.nama LIKE :kode");
            q.setParameter("kode", "%" + nama + "%");
            listSiswa = q.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return listSiswa;
    }

    @Override
    public List<Siswa> findAllByKelas(String kelas) {
        List<Siswa> listSiswa = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT s FROM Siswa s WHERE s.kelas = :kode");
            q.setParameter("kode", kelas);
            listSiswa = q.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return listSiswa;
    }

    @Override
    public Siswa findOneByName(String nama) {
        Siswa siswa = new Siswa();
        connect();
        try {
            Query q = manager().createQuery("SELECT s FROM Siswa s WHERE s.nama = :kode");
            q.setParameter("kode", nama);
            siswa = (Siswa) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return siswa;
    }
    
    @Override
    public void hapusSemua() {
        connect ();
        try {
            Query q = manager().createSQLQuery("TRUNCATE TABLE master_siswa");
            q.executeUpdate();
            commit();
        }catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
    }

}
