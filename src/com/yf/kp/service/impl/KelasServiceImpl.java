package com.yf.kp.service.impl;

import com.yf.kp.model.Kelas;
import com.yf.kp.service.KelasService;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class KelasServiceImpl extends AbstractServiceImpl<Kelas> implements KelasService {

    public KelasServiceImpl() {
        super(Kelas.class);
    }

    @Override
    public Kelas findOneByName(String namaKelas) {
        Kelas kelas = new Kelas();
        connect();
        try {
            Query q = manager().createQuery("SELECT K FROM Kelas K WHERE K.nama_kelas =: kelas");
            q.setParameter("kelas", namaKelas);
            kelas = (Kelas) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return kelas;
    }

}
